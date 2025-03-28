# 📝 LexChain: Exploring Blockchain for Legal Contracts

Welcome to **LexChain**! This project is a **proof of concept (POC)** designed to explore how **blockchain technology** can be applied to the management of legal contracts. It is not intended to be used in production environments, but rather serves as a personal exploration into the potential of using blockchain for increasing transparency, security, and traceability in legal agreements.

<p align="center">
  <img src="https://img.shields.io/badge/Solidity-2E8B57?style=for-the-badge&logo=solidity&logoColor=white" />
  <img src="https://img.shields.io/badge/Alchemy-039BE5?style=for-the-badge&logo=alchemy&logoColor=white" />
  <img src="https://img.shields.io/badge/Remix IDE-3e5f8a?style=for-the-badge&logo=remix&logoColor=white" />
  <img src="https://img.shields.io/badge/Hardhat-E6522C?style=for-the-badge&logo=hardhat&logoColor=white" />
  <img src="https://img.shields.io/badge/Polygon-854ce6?style=for-the-badge&logo=Polygon&logoColor=white" />
  <img src="https://img.shields.io/badge/Smart%20Contracts-8B0000?style=for-the-badge&logo=Polygon&logoColor=white" />
</p>

## ⚠️ Disclaimer

**LexChain** is an experimental project and should be considered a **proof of concept** rather than a fully developed product. It aims to investigate the possibility of using blockchain technology to manage legal contracts in a secure, transparent, and immutable way. This project is not intended for real-world applications at this stage and is not meant to replace traditional legal frameworks.

This project explores the potential use of **smart contracts** to:
- Securely store legal agreements
- Track the signing process
- Provide transparency and traceability of contract changes

## 🌟 Key Features of LexChain:

* 🔒 **Blockchain-based Security**: The project uses **smart contracts** on Ethereum to store legal agreements in a secure and immutable way, ensuring the integrity of the contract.
* 🕵️ **Transparent Tracking of Signatures**: LexChain allows for the tracking of contract signatures by different parties, offering clear visibility into the status of the contract.
* ⏰ **Contract Expiration**: Contracts can have expiration times to ensure that agreements are executed within specific timeframes.
* ⚖️ **Dispute Resolution**: The project includes a basic framework for raising and resolving disputes related to the contract, allowing for the involvement of an arbitrator.

## 📝 Purpose

The primary goal of **LexChain** is to **explore** how **blockchain** can help in managing **legal contracts**. Some of the aspects being evaluated include:

- Using smart contracts to automate parts of the contract lifecycle
- Tracking changes and updates to contracts in a transparent and immutable way
- Providing a mechanism for dispute resolution and contract execution

## 🛠️ Installation
First, clone the repository:

```bash
git clone https://github.com/sergio11/lexchain_blockchain.git
cd lexchain_blockchain
```

Install the necessary dependencies:

```bash
npm install
```

## 📜 Usage

**LexChain** is currently deployed and tested on the **Ethereum test networks**. The smart contracts can be used for experimenting with the contract creation, signing process, dispute resolution, and contract execution.

### Key Actions in LexChain:
- **Create Contract**: Initiate a contract with two parties and specify terms, expiration time, and contract details.
- **Sign Contract**: Both parties can sign the contract, after which the contract moves to a "Signed" state.
- **Execute Contract**: Once signed, the owner can execute the contract, marking it as "Executed."
- **Dispute Resolution**: A party can raise a dispute if there is a disagreement, and an arbitrator can resolve the dispute.

## ⚙️ Smart Contracts

LexChain leverages **Solidity** smart contracts for contract management, utilizing Ethereum's blockchain for data integrity and transparency. The system works by allowing parties to sign, execute, and resolve disputes with the help of **smart contracts**.

```solidity
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
```

Note: The implementation of smart contracts in this POC is for learning purposes and may need additional features for real-world applications.

## 🧪 Testing

Testing is an essential part of this project to ensure the functionality of contract creation, signing, dispute resolution, and contract execution. The tests are designed to ensure the contracts behave as expected in various scenarios.

### Running Tests

Tests are executed to validate that the core functionalities, like signing, executing, and handling disputes, are functioning correctly.

## 📜 Roadmap

- **Phase 1: Contract Creation & Signing**: Develop and test the ability to create and sign contracts on the blockchain.
- **Phase 2: Dispute Handling**: Implement and test dispute resolution mechanisms.
- **Phase 3: Contract Execution**: Enable the execution of contracts once all parties have signed.
- **Phase 4: Evaluation and Improvements**: Analyze the feasibility and efficiency of using blockchain in legal processes and improve the system based on findings.

## 🤝 Contributing

Since this is a personal **proof of concept**, contributions are not expected, but feel free to open issues for discussions or suggestions on how this project could evolve in the future.
