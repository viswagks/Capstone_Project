import pytest
from utility import util
from selenium.webdriver.common.by import By

class TestClass():

    @pytest.fixture
    def fix(self):
        util.init()
        util.navigateTo("http://the-internet.herokuapp.com/")
        yield
        util.tearDown()
    def test_1(self, fix):
        assert util.getTitle() == "The Internet"

    def test_2(self, fix):
        util.clickElement(By.LINK_TEXT, "Checkboxes")
        assert util.getAttribute(By.XPATH, "//h3[text()='Checkboxes']", "Displayed") is True
        assert util.getAttribute(By.XPATH, "//input[@type='checkbox'][1]", "Selected") is False
        assert util.getAttribute(By.XPATH, "//input[@type='checkbox'][2]", "Selected") is True
        util.driver.back()

    def test_3(self, fix):
        util.clickElement(By.LINK_TEXT, "File Upload")
        assert util.getAttribute(By.XPATH, "//h3[text()='File Uploader']", "Displayed") is True
        util.sendKeys(By.ID, "file-upload", __file__)
        util.clickElement(By.ID, "file-submit")
        