# Quotes API tests

This is the Test Suite created for testing the following 2 endpoints in FavQs API.
- List Quotes
- Fav Quote

## Getting Started

1. Clone the project. `https://github.com/suchinfy/HMHCodingTest.git`
2. Open the `HMHCodingTest` folder with IntelliJ/Eclipse. Use auto-import for a Maven
   project.

## Running the Tests

We need to give provide some parameters to run the tests. These details are user credentials for favQs account along with the generated API key/auth token. 

- For Eclipse- 
  - Right click on test class or testNG.xml -> Run As -> Run Configurations.
  - Click on Arguments tab. In VM arguments section, enter the following arguments.
  -  -Dlogin=your_email
     -Dpassword=your_password
     -DauthToken=your_api_key
-  For IntelliJ
   - Right click on test class or testNG.xml -> More Run/Debug -> Modify Run Configurations.
   - In JDK settings, for VM options enter the following- 
   - -Dlogin=your_email
    -Dpassword=your_password
    -DauthToken=your_api_key
  - Run the tests

## Additional test cases which are not included-
### List quotes-
1. Test list quotes by providing page parameters
2. Test list quotes with different combination of filters

### Fav Quote
1. Test mark favorite without providing the user token
2. Test unmark favorite without providing user token

We can also perform Performance testing of both List Quotes and Fav Quote APIs like load testing and Stress testing. This can be achieved with automation using Gatling.

