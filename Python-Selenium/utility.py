from selenium import webdriver
from selenium.webdriver.common.by import By

class util:
    driver = None
    def init():
        util.driver = driver = webdriver.Chrome()
        util.driver.maximize_window()
        util.driver.implicitly_wait(10)

    def navigateTo(url):
        util.driver.get(url)

    def getTitle():
        return util.driver.title

    def getElement(type, id):
        return util.driver.find_element(type, id)
    
    def clickElement(type, id):
        util.getElement(type, id).click()

    def getAttribute(type, id, attribute):
        if (attribute == "Displayed"):
            return util.getElement(type, id).is_displayed()
        elif (attribute == "Selected"):
            return util.getElement(type, id).is_selected()
        elif (attribute == "Text"):
            return util.getElement(type, id).text
        else:
            return util.getElement(type, id).get_attribute(attribute)
        
    def sendKeys(type, id, key):
        util.getElement(type, id).send_keys(key)

    def tearDown():
        util.driver.quit()