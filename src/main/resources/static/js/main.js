let pages = document.getElementsByClassName('page');
let counter = 0;
let scrollStatus = true;
let headerButtons = [].slice.call(document.getElementsByClassName('header-button'));
let defaultStyleHeaderButton = `
    font-weight: 300;
    border-bottom: 1px solid #ededed;`;
let defaultStyleHeaderButtonHover = `
    border-bottom: 1px solid #444444;
    cursor: pointer;
    font-weight: 300;`;
let selectedStyleHeaderButton = `
    font-weight: 700;
    border-bottom: 1px solid #ededed;`;
let selectedStyleHeaderButtonHover = `
    cursor: default;`;

function setHeaderButtonsOnClick() {
    headerButtons.map(function (headerButton, index) {
        headerButton.onclick = function () {
            if (counter != index) {
                counter = index;
                displayPage();
            }
        }
    });
}

function setOnWheel() {
    window.onwheel = e => {
        if (scrollStatus == true) {
            if (e.deltaY >= 0) {
                if (counter < pages.length - 1) {
                    counter++;
                }
            } else {
                if (counter > 0) {
                    counter--;
                }
            }
            displayPage();
            setTimeout(() => { scrollStatus = true }, 500);
            scrollStatus = false;
        }
    }
}

function displayPage() {
    [].slice.call(pages).map(function (page) { page.hidden = true; });
    pages[counter].hidden = false;
    updateHeaderButtons();
}

function updateHeaderButtons() {



    headerButtons.map(function (headerButton, index) {
     headerButton.style = defaultStyleHeaderButton;
        headerButton.onmouseover = function () {
            this.style = defaultStyleHeaderButtonHover;
        }
        headerButton.onmouseout = function () {
            this.style = defaultStyleHeaderButton;
        }
        if (index == counter) {
            headerButton.style = selectedStyleHeaderButton;
headerButton.onmouseover = function () {
            this.style = selectedStyleHeaderButton;
        }
        headerButton.onmouseout = function () {
            this.style = selectedStyleHeaderButton;
        }
        }
    });

}

function main() {
    displayPage();
    setHeaderButtonsOnClick();
    setOnWheel();
}

main();