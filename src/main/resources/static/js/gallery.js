let imageContainer = document.querySelector('.image-container');
let leftBar = document.querySelector('.left-bar');
let midBar = document.querySelector('.mid-bar');
let rightBar = document.querySelector('.right-bar');
let progressBar = document.querySelector('.progress-bar');

let imageArray = [];
let modes = [swing, benches, tables];
let upButtonBottomBorders = [].slice.call(document.getElementsByClassName('up-button-bottom-border'));
let upButtons = [].slice.call(document.getElementsByClassName('up-button'));
let sideButtons = [].slice.call(document.getElementsByClassName('side-button'));
let imageCounter = 0;
let mode = 0;

let segmentWidth = 0;

let sideButtonAnim = `
    animation-duration: 0.5s;
    animation-name: sideButtonHover;
    animation-fill-mode: forwards;`;

let sideButtonAnimReverse = `  
    animation-duration: 0.5s;
    animation-name: sideButtonHoverReverse;
    animation-fill-mode: forwards;`;

let defaultSideButton = `  
    animation-duration: 0s;
    animation-name: sideButtonHoverReverse;
    animation-fill-mode: forwards;`;

let clickSideButton = `  
    animation-duration: 1s;
    animation-name: sideButtonClick;
    animation-fill-mode: forwards;
    `;

let imageAnim = `  
    animation-duration: 0.5s;
    animation-name: imageAnim;
    animation-fill-mode: forwards;
    `;

let imageAnimReverse = `  
    animation-duration: 0.5s;
    animation-name: imageAnimReverse;
    animation-fill-mode: forwards;
    `;

let progressBarAnim = `  
    animation-duration: 0.5s;
    animation-name: progressBarAnim;
    animation-fill-mode: forwards;
    `;

let progressBarAnimReverse = `  
    animation-duration: 0.5s;
    animation-name: progressbarAnimReverse;
    animation-fill-mode: forwards;
    `;

function loadImage() {
    imageContainer.style = imageAnim;
    midBar.style = progressBarAnim;
    midBar.style.width = segmentWidth + 'px';
    setTimeout(function () {
        imageContainer.src = '/sources/gallery/' + modes[mode][imageCounter];
    }, 500);
    imageContainer.onload = function () { imageContainer.style = imageAnimReverse; progressBarWork(); midBar.style = progressBarAnimReverse;midBar.style.width = segmentWidth + 'px'; };
}

function progressBarWork() {
    progressBar.style.width = getComputedStyle(imageContainer).width;
    segmentWidth = parseInt(getComputedStyle(imageContainer).width, 10) / modes[mode].length;
    midBar.style.width = segmentWidth + 'px';
    leftBar.style.width = segmentWidth * imageCounter + 'px';
    rightBar.style.width = (modes[mode].length - 1 - imageCounter) * segmentWidth + 'px';
}

function progressBarInit() {

}


function onSideButtonClick() {
    sideButtons.map(function (sideButton, index) {
        sideButton.onclick = function () {
            if (index == 0 && imageCounter > 0) {
                imageCounter--;
                sideButton.style = clickSideButton;
                loadImage();
            }
            if (index == 1 && imageCounter < modes[mode].length - 1) {
                imageCounter++;
                sideButton.style = clickSideButton;
                loadImage();
            }
            if (index == 0 && imageCounter == 0) {
                sideButton.style = sideButtonAnimReverse;
            }
            if (index == 1 && imageCounter == modes[mode].length - 1) {
                sideButton.style = sideButtonAnimReverse;
            }
            pictureLimiter();
            // progressBarWork();
        }
    });
}

function onUpButtonClick() {
    upButtons.map(function (upButton, index) {
        upButton.onclick = function () {
            if (mode != index) {
                mode = index;
                imageCounter = 0;
                loadImage();
                pictureLimiter();
                applyingStandartBorderStyle(upButtonBottomBorders[index]);
                // progressBarInit();
            }
            updateAButtonsAnim(mode, upButtons);
            updateBottomBordersAnim(mode, upButtons, upButtonBottomBorders);
        }
    });
}


function applyHoverStyles(someButton, onAnim, outAnim) {
    someButton.onmouseout = function () {
        someButton.style = outAnim;
    };
    someButton.onmouseover = function () {
        someButton.style = onAnim;
    };
}

function pictureLimiter() {
    if (imageCounter == 0) {
        // sideButtons[0].style = defaultSideButton;
        applyHoverStyles(sideButtons[0], defaultSideButton, defaultSideButton);
    }
    else {
        // sideButtons[0].style = 'opacity: 100%; cursor: pointer;';
        applyHoverStyles(sideButtons[0], sideButtonAnim, sideButtonAnimReverse);

    }
    if (imageCounter == modes[mode].length - 1) {
        // sideButtons[1].style = defaultSideButton;
        sideButtons[1].style = sideButtonAnimReverse;
        applyHoverStyles(sideButtons[1], defaultSideButton, defaultSideButton);

    }
    else {
        // sideButtons[1].style = 'opacity: 100%; cursor: pointer;';
        applyHoverStyles(sideButtons[1], sideButtonAnim, sideButtonAnimReverse);

    }
}

function main() {
    loadImage();
    // progressBarInit();
    updateAButtonsAnim(imageCounter, upButtons);
    updateBottomBordersAnim(imageCounter, upButtons, upButtonBottomBorders);
    onUpButtonClick();
    onSideButtonClick();
    pictureLimiter();
}



main();