# slack-api-tests
Sample test suite to automate slack rest apis with rest assured and cucumber framework

### Scenario
```
Scenario: Test to verify a user with api token can create/join, rename and delete channels
    Given I create and join a new channel "apitestchannel"
    And I rename the created channel
    Then I should list all Channels and Validate if the Channel name has changed successfully
    And I archive the created channel
    Then I should Validate if the Channel is archived successfully
```

### Requirements
```
Java 8 or higher
Maven
Slack account with api token generated
```

## Configuration

### Test enviroment details

Can be changed in file autotestConfig.properties under src/test/resources. 
Api token has to be generated and changed.
```
baseUrl=https://slack.com/api
token=xoxp-950414119825-960538021664-947761313714-6a774fe07eadc0dd2397b0f066c1c622
```

## Usage
To run the test
```
mvn clean test
```

## Reports

Reports can ve viewed in the directory -> target/cucumberReports/index.html
