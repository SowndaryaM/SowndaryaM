*** Settings ***
Library    SeleniumLibrary
Library    OperatingSystem
Library    String
Library    Evaluate


*** Variables ***
${BROWSER}        Chrome
${URL}            http://automationexercise.com


*** Test Cases ***
Perform Product Purchase
    Open Browser To Page
    Click Products Menu
    Verify Search Bar Visibility
    Search Product    men tshirt
    Click Search Button
    Verify Product Visibility    men tshirt
    Scroll Window    0    400
    Add Product To Cart
    Clear Text Box    EMPTY
    Search Product    top
    Click Search Button
    Verify Product Visibility    top
    Scroll Window    0    400
    Add Product To Cart
    Click Cart Button
    Verify Products In Cart
    Verify Total Product Value

*** Keywords ***
Open Browser To Page
    Open Browser    ${URL}    ${BROWSER}
    Maximize Browser Window
    Title Should Be    Automation Exercise

Click Products Menu
    
    Click Link    xpath=//a[@href='/products']
    Title Should Be    Automation Exercise - All Products

Verify Search Bar Visibility
    Page Should Contain Element    xpath=//input[@id='search_product']

Search Product
    [Arguments]    ${product}
    Input Text    xpath=//input[@id='search_product']    ${product}

Click Search Button
    Click Button    xpath=//button[@id='submit_search']
    Sleep     10s

Verify Product Visibility
    [Arguments]    ${product}
    Page Should Contain Element   xpath=//div[@class='features_items']//div[@class='col-sm-4']//div[@class='overlay-content']/p  ${product}
    
Scroll Window
   [Arguments]    ${x_offset}    ${y_offset}
   Execute JavaScript    window.scrollBy(${x_offset}, ${y_offset})    

Add Product To Cart
  
    Sleep     5s
    Mouse Over    xpath=//div[@class='product-image-wrapper']
    Click Element    xpath=//div[@class='features_items']//div[@class='col-sm-4']//div[@class='overlay-content']/a[@class='btn btn-default add-to-cart']
    Sleep     5s
    Click Button    xpath=//button[@class='btn btn-success close-modal btn-block']
    
Clear Text Box
    [Arguments]    ${EMPTY}
      Input Text    xpath=//input[@id='search_product']    ${EMPTY}     

Click Cart Button
    Click Link    Cart
    Title Should Be    Automation Exercise - Checkout

Verify Products In Cart
     Page Should Contain Element     xpath=//a[contains(text(), 'Men Tshirt')]
     Page Should Contain Element    xpath=//a[contains(text(), 'Blue Top')]

Verify Total Product Value
   @{elements}    Get Web Elements    xpath=//td[@class='cart_total']/p[contains(text(),'')]
   ${total_sum}    Set Variable    0  
   FOR     ${element}     IN     @{elements}
           ${element_text}    Get Text     ${element}
           Log   ${element_text}  
     ${value}  Get Regexp Matches    ${element_text}      \\d+
     Log    ${value}[0]
    ${integer_value}    Convert To Integer    ${value}[0]
    Log    ${integer_value}
    ${total_sum}    Evaluate    ${total_sum} + ${integer_value}
    END  
    Log     Total Sum: ${total_sum}
    Should Be Equal As Numbers    ${total_sum}    900
   
   