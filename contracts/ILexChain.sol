// SPDX-License-Identifier: MIT 
pragma solidity ^0.8.19;

/**
 * @title ILexChain
 * @dev Interface for the LexChain contract.
 * This interface defines the core functionalities for managing legal agreements on the blockchain.
 */
interface ILexChain {
    /**
     * @dev Enum representing the possible states of a contract.
     * - Draft: The contract is created but not yet signed.
     * - Signed: Both parties have signed the contract.
     * - Executed: The contract has been executed.
     * - Disputed: A dispute has been raised regarding the contract.
     * - Expired: The contract has passed its expiration time without being signed or executed.
     */
    enum State { Draft, Signed, Executed, Disputed, Expired }

    /// @notice Event emitted when a new contract is created.
    event ContractCreated(uint256 indexed contractId, address indexed owner);

    /// @notice Event emitted when a contract is signed by a party.
    event ContractSigned(uint256 indexed contractId, address indexed signer);

    /// @notice Event emitted when a contract is executed.
    event ContractExecuted(uint256 indexed contractId);

    /// @notice Event emitted when a dispute is raised.
    event DisputeRaised(uint256 indexed contractId, address indexed raiser);

    /// @notice Event emitted when a dispute is resolved.
    event DisputeResolved(uint256 indexed contractId, address indexed arbitrator);

    /// @notice Event emitted when a contract expires.
    event ContractExpired(uint256 indexed contractId);

    /**
     * @notice Creates a new contract agreement with an expiration time.
     * @param _partyA Address of the first participant.
     * @param _partyB Address of the second participant.
     * @param _docHash IPFS hash of the contract document.
     * @param _expirationTime Time in seconds when the contract should expire.
     */
    function createContract(address _partyA, address _partyB, string memory _docHash, uint256 _expirationTime) external;

    /**
     * @notice Allows a party to sign the contract.
     * @param _id Contract ID.
     */
    function signContract(uint256 _id) external;

    /**
     * @notice Executes the contract, finalizing the agreement.
     * @param _id Contract ID.
     */
    function executeContract(uint256 _id) external;

    /**
     * @notice Allows a party to raise a dispute on the contract.
     * @param _id Contract ID.
     */
    function raiseDispute(uint256 _id) external;

    /**
     * @notice Resolves a dispute raised on the contract.
     * @param _id Contract ID.
     * @param _arbitrator Address of the arbitrator who resolves the dispute.
     */
    function resolveDispute(uint256 _id, address _arbitrator) external;

    /**
     * @notice Retrieves contract details by ID.
     * @param _id Contract ID.
     * @return owner Address of the contract creator.
     * @return partyA Address of the first participant.
     * @return partyB Address of the second participant.
     * @return documentHash IPFS hash of the contract document.
     * @return state Current state of the contract.
     * @return disputeRaised Boolean indicating whether a dispute has been raised.
     * @return disputeResolution Address of the arbitrator (if any).
     * @return expirationTime Time by which the contract must be signed/executed.
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
    );

    /**
     * @notice Checks if the contract has expired.
     * @param _id Contract ID.
     */
    function checkExpiration(uint256 _id) external;
}