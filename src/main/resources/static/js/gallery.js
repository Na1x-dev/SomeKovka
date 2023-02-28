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

function loadImage() {
    imageContainer.src = '/sources/gallery/' + modes[mode][imageCounter]; 
    imageContainer.onload = function(){progressBarWork();};
}

function progressBarWork() {
    // let prbw = imageArray;
    // midBar.style.width = parseInt(getComputedStyle(progressBar).width, 10)/imageArray.length;
    // console.log(prbw);
    // lowerBar.style.width = "300px";
    progressBar.style.width = getComputedStyle(imageContainer).width; 
    segmentWidth = parseInt(getComputedStyle(imageContainer).width, 10) / modes[mode].length;
    midBar.style.width = segmentWidth + 'px';
    leftBar.style.width = segmentWidth * imageCounter + 'px';
    rightBar.style.width = (modes[mode].length - 1 - imageCounter) * segmentWidth + 'px';
}

function progressBarInit() {
    // progressBar.style.width = getComputedStyle(imageContainer).width; 
    // segmentWidth = parseInt(getComputedStyle(imageContainer).width, 10) / modes[mode].length;
    // midBar.style.width = segmentWidth + 'px';
    // // console.log(getComputedStyle(midBar).width);
    // leftBar.style.width = 0 + 'px';
    
    // segmentWidth = (parseInt(getComputedStyle(progressBar).width, 10) / imageArray.length) + 'px';
  

}


// n - картинок
// wl - ширина нижнего бара
// wu - ширина верхнего бара
// imageCounter - текущая картинка
// w - полная ширина бара 

// wl = (w/n) * (1 + imageCounter)
// wl = (w/n) * imageCounter


function onSideButtonClick() {
    sideButtons.map(function (sideButton, index) {
        sideButton.onclick = function () {
            if (index == 0 && imageCounter > 0) {
                imageCounter--;
                sideButton.style = clickSideButton;
            }
            if (index == 1 && imageCounter < modes[mode].length - 1) {
                imageCounter++;
                sideButton.style = clickSideButton;
            }
            if (index == 0 && imageCounter == 0) {
                sideButton.style = sideButtonAnimReverse;
            }
            if (index == 1 && imageCounter == modes[mode].length - 1) {
                sideButton.style = sideButtonAnimReverse;
            }
            loadImage();
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