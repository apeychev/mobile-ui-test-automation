@mobile @search @cleanUp
Feature: Search for cars and jeeps

  1.Navigate to home page
  2.Select car with brand Audi and Maximal Price Range
  3.Verify that the first car in the result is Audi and the price is under the entered range

  Scenario Outline: Search for car brand under certain maximal price range
    When Navigate To Web Address With BaseUrl
    Then Verify Home Page Is Displayed
    When Select "<CarBrand>" From Car Brand Dropdown List
    Then Verify "<CarBrand>" Is Selected
    When I Type "<MaximalPrice>" Into MaximalPrice Text Field
    When Click Search Button
    Then Verify That Results Page Is Displayed
    Then Verify First Vehicle Price Can Be Parsed
    Then Verify First Vehicle Price Is Below <MaximalPrice>
    Then Verify Vehicle Name Is "<CarBrand>"

    Examples:
      | MaximalPrice | CarBrand |
      | 5000         |  Audi    |
      | 10000        |  Audi    |
      | 30000        |  Audi    |