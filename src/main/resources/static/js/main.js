let pages = [].slice.call(document.getElementsByClassName('page'));
let counter = 0;
let scrollStatus = true;
let headerButtons = [].slice.call(document.getElementsByClassName('header-button'));
let headerButtonBottomBorders = [].slice.call(document.getElementsByClassName('header-button-bottom-border'));
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

let textSizeUpAnim = `
    animation-duration: 0.5s;
    animation-name: textSizeUp;
    animation-fill-mode: forwards;`;

let textSizeDownAnim = `
    animation-duration: 0.5s;
    animation-name: textSizeDown;
    animation-fill-mode: forwards;`;

let pageShowUpAnim = `
    animation-duration: 0.5s;
    animation-name: pageShowUp;
    animation-fill-mode: forwards;`;

let pageShowDownAnim = `
    animation-duration: 0.5s;
    animation-name: pageShowDown;
    animation-fill-mode: forwards;`;

let headerButtonBottomBorderAnim = `
    animation-duration: 0.5s;
    animation-name: headerButtonHover;
    animation-fill-mode: forwards;`;

let headerButtonBottomBorderAnimReverse = `  
    animation-duration: 0.5s;
    animation-name: headerButtonHoverReverse;
    animation-fill-mode: forwards;`;

let initAnimationStyle = `
    animation-duration: 1.5s;
    animation-name: initAnim;
    animation-fill-mode: forwards;`;

let defaultHeaderButtonBorder = `  
    animation-duration: 0s;
    animation-name: headerButtonHoverReverse;
    animation-fill-mode: forwards;`;

function setHeaderButtonsOnClick() {
    headerButtons.map(function (headerButton, index) {
        headerButton.onclick = function () {
            if (counter != index) {
                counter = index;
                headerButtonBottomBorders[index].style = headerButtonBottomBorderAnimReverse;
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
    updateHeaderButtonsAnim();
    updatePagesAnim();
    updateBottomBordersAnim();
}


function updateHeaderButtonsAnim() {
    headerButtons.map(function (headerButton, index) {
        if (getComputedStyle(headerButton).fontSize > "25px" && index != counter) {
            headerButton.style = textSizeDownAnim;
        }

        else if (getComputedStyle(headerButton).fontSize < "30px" && index == counter) {
            headerButton.style = textSizeUpAnim;
        };
    });
}

function updatePagesAnim() {
    pages.map(function (page, index) {
        if (getComputedStyle(page).height > "0" && index != counter) {
            page.style = pageShowDownAnim;
        }

        else if (getComputedStyle(page).height < "100vh" && index == counter) {
            page.style = pageShowUpAnim;
        };
    });
}

function updateBottomBordersAnim() {
    headerButtons.map(function (headerButton, index) {
        if (index != counter) {

            headerButton.onmouseout = function () {
                headerButtonBottomBorders[index].style = headerButtonBottomBorderAnimReverse;
            };
            headerButton.onmouseover = function () {
                headerButtonBottomBorders[index].style = headerButtonBottomBorderAnim;
            };
        }
        if (index == counter) {
            headerButton.onmouseout = function () {
                headerButtonBottomBorders[index].style = defaultHeaderButtonBorder;
            };
            headerButton.onmouseover = function () {
                headerButtonBottomBorders[index].style = defaultHeaderButtonBorder;
            };
        }
    });
}

function initAnimation() {
    let body = document.querySelector('body');
    body.style = initAnimationStyle;
}

function main() {
    initAnimation();
    displayPage();
    setHeaderButtonsOnClick();
    setOnWheel();
}

main();

