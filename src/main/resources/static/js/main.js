let pages = [].slice.call(document.getElementsByClassName('page'));
let counter = 0;
let scrollStatus = true;
let headerButtons = [].slice.call(document.getElementsByClassName('header-button'));
let defaultStyleHeaderButton = `
    font-weight: 400;
    border-bottom: 1px solid #ededed;`;
let defaultStyleHeaderButtonHover = `
    border-bottom: 1px solid #444444;
    cursor: pointer;
    font-weight: 400;`;
let selectedStyleHeaderButton = `
    font-weight: 700;
    border-bottom: 1px solid #ededed;`;
let selectedStyleHeaderButtonHover = `
    cursor: default;`;

let selectedStyleHeaderButtonAnimSize = `
        animation-duration: 0.5s;
        animation-name: textSizeUp;
        animation-fill-mode: forwards;`;

let selectedStyleHeaderButtonAnimReverseSize = `
        animation-duration: 0.5s;
        animation-name: textSizeDown;
        animation-fill-mode: forwards;`;

let selectedPageAnim = `
        animation-duration: 0.5s;
        animation-name: pageShowUp;
        animation-fill-mode: forwards;`;

let selectedPageAnimReverse = `
        animation-duration: 0.5s;
        animation-name: pageShowDown;
        animation-fill-mode: forwards;`;

let initAnimationStyle = `
animation-duration: 1.5s;
animation-name: initAnim;
animation-fill-mode: forwards;`;

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
    // [].slice.call(pages).map(function (page) { page.hidden = true; });
    // pages[counter].hidden = false;
    updateHeaderButtonsAnim();
    updatePagesAnim();
}


function updateHeaderButtonsAnim() {
    headerButtons.map(function (headerButton, index) {
        if (getComputedStyle(headerButton).fontSize > "25px" && index != counter) {
            headerButton.style = selectedStyleHeaderButtonAnimReverseSize;
        }

        else if (getComputedStyle(headerButton).fontSize < "30px" && index == counter) {
            headerButton.style = selectedStyleHeaderButtonAnimSize;
        };
    });
}

function updatePagesAnim() {
    pages.map(function (page, index) {
        if (getComputedStyle(page).height > "0" && index != counter) {
            page.style = selectedPageAnimReverse;
        }

        else if (getComputedStyle(page).height < "100vh" && index == counter) {
            page.style = selectedPageAnim;
        };
    });
}

function initAnimation(){
    let body = document.querySelector('body');
    body.style = initAnimationStyle;
}

function main() {
    initAnimation();
    headerButtons.map(function (headerButton, index) { headerButton = defaultStyleHeaderButton; });
    displayPage();
    setHeaderButtonsOnClick();
    setOnWheel();
}

main();

