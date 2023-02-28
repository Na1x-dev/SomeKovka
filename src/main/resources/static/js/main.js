let pages = [].slice.call(document.getElementsByClassName('page'));
let counter = 0;
let scrollStatus = true;
let headerButtons = [].slice.call(document.getElementsByClassName('header-button'));
let headerButtonBottomBorders = [].slice.call(document.getElementsByClassName('header-button-bottom-border'));
let defaultStyleAButton = `
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

let aButtonBottomBorderAnim = `
    animation-duration: 0.5s;
    animation-name: aButtonBorderHover;
    animation-fill-mode: forwards;`;

let aButtonBottomBorderAnimReverse = `  
    animation-duration: 0.5s;
    animation-name: aButtonBorderHoverReverse;
    animation-fill-mode: forwards;`;

let initAnimationStyle = `
    animation-duration: 1.5s;
    animation-name: initAnim;
    animation-fill-mode: forwards;`;

let defaultAButtonBorder = `  
    animation-duration: 0s;
    animation-name: aButtonHoverReverse;
    animation-fill-mode: forwards;`;

function setHeaderButtonsOnClick() {
    headerButtons.map(function (headerButton, index) {
        headerButton.onclick = function () {
            if (counter != index) {
                counter = index;
                applyingStandartBorderStyle(headerButtonBottomBorders[index]);
                displayPage();
            }
        };
    });
}

function applyingStandartBorderStyle(someButtonBorder){
    someButtonBorder.style = aButtonBottomBorderAnimReverse;     
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
    updateAButtonsAnim(counter, headerButtons);
    updateBottomBordersAnim(counter, headerButtons, headerButtonBottomBorders);
    updatePagesAnim();
}


function updateAButtonsAnim(someCounter, someButtons) {
    someButtons.map(function (someButton, index) {
        if (getComputedStyle(someButton).fontSize > "25px" && index != someCounter) {
            someButton.style = textSizeDownAnim;
        }
        else if (getComputedStyle(someButton).fontSize < "30px" && index == someCounter) {
            someButton.style = textSizeUpAnim;
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
        }
    });
}

function updateBottomBordersAnim(someCounter, someButtons, someButtonBottomBorders) {
    someButtons.map(function (someButton, index) {
        
        if (index != someCounter) {
            someButton.onmouseout = function () {
                someButtonBottomBorders[index].style = aButtonBottomBorderAnimReverse;
            };
            someButton.onmouseover = function () {
                someButtonBottomBorders[index].style = aButtonBottomBorderAnim;
            };
            
        }
        if (index == someCounter) {
            someButton.onmouseout = function () {
                someButtonBottomBorders[index].style = defaultAButtonBorder;
            };
            someButton.onmouseover = function () {
                someButtonBottomBorders[index].style = defaultAButtonBorder;
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

