# ChargeLab Assessment

### Allure Report: https://bakedzebra.github.io/chargelab/

## Challenge 1

- RestAssured was selected as a tool needed to perform automated testing of APIs. It's lightweight and easy-to-integrate with JUnit framework
- A simple Github workflow was added that runs test when changes are pushed to `main` branch or pull request is created. Allure dependency was also added to generate a report of completed tests and their results
- Main approach was to test possible cases of status responses, authorized and unauthorized requests and performance (run time). If assessment included more endpoints to test - it possibly included testing of allowed values for filters and request parameters
- ChatGPT was used to make response body validation easier and faster

## Challenge 2

- Selenium was selected as a tool needed to perform automated testing of APIs. It's commonly used and good fit for Java
- It was also added to CI pipeline to be run in parallel with API tests
- Login, Logout and Charging actions were chosen for testing to cover initial and complete flow for demonstration of how to achieve different types of results
- ChatGPT was used to validate and assist in XPath definition

