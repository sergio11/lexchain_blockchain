const { expect } = require("chai");
const { ethers } = require("hardhat");

describe("LexChain Contract", function () {

  async function deployContractFixture() {
    const [owner, partyA, partyB, arbitrator] = await ethers.getSigners();
    const LexChain = await ethers.getContractFactory("LexChain");
    const instance = await LexChain.deploy();
    return { LexChain, instance, owner, partyA, partyB, arbitrator };
  }

  // Test to verify contract creation
  it("Should create a new contract", async function () {
    const { instance, owner, partyA, partyB } = await deployContractFixture();
    const docHash = "Qm...";  // Replace with an example IPFS hash
    const expirationTime = Math.floor(Date.now() / 1000) + 60 * 60 * 24;  // 24 hours from now
    await expect(instance.createContract(partyA.address, partyB.address, docHash, expirationTime))
      .to.emit(instance, 'ContractCreated')
      .withArgs(1, owner.address);
    
    const contract = await instance.getContract(1);
    expect(contract.owner).to.equal(owner.address);
    expect(contract.partyA).to.equal(partyA.address);
    expect(contract.partyB).to.equal(partyB.address);
    expect(contract.documentHash).to.equal(docHash);
    expect(contract.state).to.equal(0); // Draft state
  });

  // Test to verify contract signing
  it("Should allow parties to sign the contract", async function () {
    const { instance, owner, partyA, partyB } = await deployContractFixture();
    const docHash = "Qm...";  // Example document hash
    const expirationTime = Math.floor(Date.now() / 1000) + 60 * 60 * 24; // Expiration time set to 24 hours from now
    
    // Create the contract between partyA and partyB
    await instance.createContract(partyA.address, partyB.address, docHash, expirationTime);
    
    // Party A signs the contract. No event expected yet.
    await instance.connect(partyA).signContract(1);
    
    // Party B signs the contract. This should trigger the ContractSigned event since both parties are signing.
    await expect(instance.connect(partyB).signContract(1))
      .to.emit(instance, 'ContractSigned')  // The event will be emitted here once both have signed
      .withArgs(1, partyB.address);  // Expect the event to include the contract ID and the address of the signer (partyB)

    // Retrieve the contract and check its state
    const contract = await instance.getContract(1);
    expect(contract.state).to.equal(1); // State 1 corresponds to 'Signed' state
  });


  // Test to verify contract execution
  it("Should allow contract execution after signing", async function () {
    const { instance, owner, partyA, partyB } = await deployContractFixture();
    const docHash = "Qm...";
    const expirationTime = Math.floor(Date.now() / 1000) + 60 * 60 * 24; // 24 hours
    await instance.createContract(partyA.address, partyB.address, docHash, expirationTime);
    
    await instance.connect(partyA).signContract(1);
    await instance.connect(partyB).signContract(1);
    
    await expect(instance.connect(owner).executeContract(1))
      .to.emit(instance, 'ContractExecuted')
      .withArgs(1);

    const contract = await instance.getContract(1);
    expect(contract.state).to.equal(2); // Executed state
  });

  // Test to raise a dispute
  it("Should allow a party to raise a dispute", async function () {
    const { instance, partyA, partyB } = await deployContractFixture();
    const docHash = "Qm...";
    const expirationTime = Math.floor(Date.now() / 1000) + 60 * 60 * 24; // 24 hours
    await instance.createContract(partyA.address, partyB.address, docHash, expirationTime);
    
    await instance.connect(partyA).signContract(1);
    await instance.connect(partyB).signContract(1);
    
    await expect(instance.connect(partyA).raiseDispute(1))
      .to.emit(instance, 'DisputeRaised')
      .withArgs(1, partyA.address);

    const contract = await instance.getContract(1);
    expect(contract.disputeRaised).to.equal(true);
  });

  // Test to resolve a dispute
  it("Should allow an arbitrator to resolve a dispute", async function () {
    const { instance, partyA, partyB, arbitrator } = await deployContractFixture();
    const docHash = "Qm...";
    const expirationTime = Math.floor(Date.now() / 1000) + 60 * 60 * 24; // 24 hours
    await instance.createContract(partyA.address, partyB.address, docHash, expirationTime);
    
    await instance.connect(partyA).signContract(1);
    await instance.connect(partyB).signContract(1);
    
    await instance.connect(partyA).raiseDispute(1);
    
    await expect(instance.connect(arbitrator).resolveDispute(1, arbitrator.address))
      .to.emit(instance, 'DisputeResolved')
      .withArgs(1, arbitrator.address);

    const contract = await instance.getContract(1);
    expect(contract.state).to.equal(2); // Executed state after dispute resolution
  });

  // Test to verify only the contract owner can execute
  it("Should not allow non-owner to execute the contract", async function () {
    const { instance, owner, partyA, partyB } = await deployContractFixture();
    const docHash = "Qm...";
    const expirationTime = Math.floor(Date.now() / 1000) + 60 * 60 * 24; // 24 hours
    await instance.createContract(partyA.address, partyB.address, docHash, expirationTime);
    
    await instance.connect(partyA).signContract(1);
    await instance.connect(partyB).signContract(1);
    
    let errorMessage = null;
    try {
      await instance.connect(partyA).executeContract(1);
    } catch (error) {
      errorMessage = error.message;
    }
    expect(errorMessage).to.contain("Only the owner can execute");
  });

  // Test to verify contract not executed without signing
  it("Should not allow execution without both parties signing", async function () {
    const { instance, owner, partyA, partyB } = await deployContractFixture();
    const docHash = "Qm...";
    const expirationTime = Math.floor(Date.now() / 1000) + 60 * 60 * 24; // 24 hours
    await instance.createContract(partyA.address, partyB.address, docHash, expirationTime);
    
    await instance.connect(partyA).signContract(1);
    
    let errorMessage = null;
    try {
      await instance.connect(owner).executeContract(1);
    } catch (error) {
      errorMessage = error.message;
    }
    expect(errorMessage).to.contain("Must be signed first");
  });

  // Test to ensure contract cannot be created with a past expiration time
  it("Should not allow contract creation with a past expiration time", async function () {
    const { instance, owner, partyA, partyB } = await deployContractFixture();
    const docHash = "Qm...";
    const expirationTime = Math.floor(Date.now() / 1000) - 60 * 60 * 24; // 24 hours in the past
    
    let errorMessage = null;
    try {
      await instance.createContract(partyA.address, partyB.address, docHash, expirationTime);
    } catch (error) {
      errorMessage = error.message;
    }
    expect(errorMessage).to.contain("Expiration time must be in the future");
  });

  // Test to check contract state transitions after a dispute is raised and resolved
  it("Should transition to DisputeResolved state after dispute is resolved", async function () {
    const { instance, partyA, partyB, arbitrator } = await deployContractFixture();
    const docHash = "Qm...";
    const expirationTime = Math.floor(Date.now() / 1000) + 60 * 60 * 24; // 24 hours from now
    await instance.createContract(partyA.address, partyB.address, docHash, expirationTime);
    
    await instance.connect(partyA).signContract(1);
    await instance.connect(partyB).signContract(1);
    
    await instance.connect(partyA).raiseDispute(1);
    
    await expect(instance.connect(arbitrator).resolveDispute(1, arbitrator.address))
      .to.emit(instance, 'DisputeResolved')
      .withArgs(1, arbitrator.address);

    const contract = await instance.getContract(1);
    expect(contract.state).to.equal(2); // Executed state or a state after resolution
  });

});