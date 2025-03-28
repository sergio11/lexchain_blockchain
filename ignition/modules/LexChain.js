const { buildModule } = require("@nomicfoundation/hardhat-ignition/modules");
const secret = require('../../.secret.json');

module.exports = buildModule("LexChain", (m) => {
  const lexChainContract = m.contract("LexChain", [secret.ownerKey]);
  return { lexChainContract };
});