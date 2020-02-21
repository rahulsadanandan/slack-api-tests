
Feature: Scnearios for channel rest apis

 
  Scenario: Test to verify a user with api token can create/join, rename and delete channels
    Given I create and join a new channel "apitestchannel"
    And I rename the created channel
    Then I should list all Channels and Validate if the Channel name has changed successfully
    And I archive the created channel
    Then I should Validate if the Channel is archived successfully


 
