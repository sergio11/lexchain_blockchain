// SPDX-License-Identifier: MIT
pragma solidity ^0.8.19;

import "./ILexChain.sol";

/**
 * @title LexChain
 * @dev A smart contract to manage legal agreements with transparency and immutability.
 * This contract enables parties to create, sign, and execute legally binding agreements
 * on the Ethereum blockchain, ensuring security and transparency.
 */
contract LexChain is ILexChain {
    /**
     * @dev Struct representing a legal contract.
     * @param owner The creator of the contract.
     * @param partyA The first signing party.
     * @param partyB The second signing party.
     * @param documentHash IPFS hash of the agreement document.
     * @param state Current state of the contract.
     * @param expirationTime Time by which the contract must be signed/executed.
     */
    struct Contract {
        address owner;
        address partyA;
        address partyB;
        string documentHash;
        State state;
        mapping(address => bool) signatures;
        bool disputeRaised;
        address disputeResolution;
        uint256 expirationTime;
    }

    /// @dev Mapping to store contracts by their ID.
    mapping(uint256 => Contract) public contracts;
    
    /// @dev Counter to keep track of contract IDs.
    uint256 public contractCount;

    /**
     * @notice Creates a new contract agreement with an expiration time.
     * @inheritdoc ILexChain
     */
    function createContract(address _partyA, address _partyB, string memory _docHash, uint256 _expirationTime) public {
        require(_expirationTime > block.timestamp, "Expiration time must be in the future");
        contractCount++;

        // Creating a new contract
        Contract storage c = contracts[contractCount];

        c.owner = msg.sender;
        c.partyA = _partyA;
        c.partyB = _partyB;
        c.documentHash = _docHash;
        c.state = State.Draft;
        c.expirationTime = _expirationTime;

        emit ContractCreated(contractCount, msg.sender);
    }

    /**
     * @notice Allows a party to sign the contract.
     * @inheritdoc ILexChain
     */
    function signContract(uint256 _id) public {
        Contract storage c = contracts[_id];

        // Check if the contract has expired
        checkExpiration(_id);

        require(msg.sender == c.partyA || msg.sender == c.partyB, "Not authorized");
        require(c.state == State.Draft, "Already signed");
        require(block.timestamp < c.expirationTime, "Contract has expired");
        
        c.signatures[msg.sender] = true;
        
        // Check if both parties have signed.
        if (c.signatures[c.partyA] && c.signatures[c.partyB]) {
            c.state = State.Signed;
            emit ContractSigned(_id, msg.sender);
        }
    }

    /**
     * @notice Executes the contract, finalizing the agreement.
     * @inheritdoc ILexChain
     */
    function executeContract(uint256 _id) public {
        Contract storage c = contracts[_id];

        // Check if the contract has expired
        checkExpiration(_id);

        require(msg.sender == c.owner, "Only the owner can execute");
        require(c.state == State.Signed, "Must be signed first");
        require(block.timestamp < c.expirationTime, "Contract has expired");
        
        c.state = State.Executed;
        emit ContractExecuted(_id);
    }

    /**
     * @notice Allows a party to raise a dispute.
     * @param _id Contract ID.
     */
    function raiseDispute(uint256 _id) public {
        Contract storage c = contracts[_id];

        // Check if the contract has expired
        checkExpiration(_id);

        require(msg.sender == c.partyA || msg.sender == c.partyB, "Not authorized");
        require(c.state == State.Signed, "Contract not signed");
        require(!c.disputeRaised, "Dispute already raised");
        
        c.disputeRaised = true;
        emit DisputeRaised(_id, msg.sender);
    }

    /**
     * @notice Resolves the dispute by an arbitrator.
     * @param _id Contract ID.
     * @param _arbitrator Address of the arbitrator.
     */
    function resolveDispute(uint256 _id, address _arbitrator) public {
        Contract storage c = contracts[_id];

        // Check if the contract has expired
        checkExpiration(_id);

        require(c.disputeRaised, "No dispute raised");
        require(msg.sender == _arbitrator, "Only the arbitrator can resolve");
        
        c.disputeResolution = _arbitrator;
        c.state = State.Executed;
        emit DisputeResolved(_id, _arbitrator);
    }

    /**
     * @notice Retrieves contract details by ID.
     * @inheritdoc ILexChain
     */
    function getContract(uint256 _id) external view returns (
        address owner,
        address partyA,
        address partyB,
        string memory documentHash,
        State state,
        bool disputeRaised,
        address disputeResolution,
        uint256 expirationTime
    ) {
        Contract storage c = contracts[_id];
        return (c.owner, c.partyA, c.partyB, c.documentHash, c.state, c.disputeRaised, c.disputeResolution, c.expirationTime);
    }

    /**
     * @notice Checks if the contract has expired and changes the state to expired if necessary.
     * @param _id Contract ID.
     */
    function checkExpiration(uint256 _id) public {
        Contract storage c = contracts[_id];
        if (block.timestamp >= c.expirationTime && c.state != State.Executed) {
            // If the contract is expired, set it to the Expired state
            c.state = State.Expired;
            emit ContractExpired(_id);
        }
    }
}