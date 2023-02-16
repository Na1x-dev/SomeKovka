let imageContainer = document.querySelector('.image-container');
let modes = ['swing', 'benches', 'tables'];
let upButtonBottomBorders = [].slice.call(document.getElementsByClassName('up-button-bottom-border'));
let upButtons = [].slice.call(document.getElementsByClassName('up-button'));
let sideButtons = [].slice.call(document.getElementsByClassName('side-button'));
let imageCounter = 0;
let mode = 0;
let img = new Image();
let isReadyToNextImage = false;

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

function loadImage() {
    img.src = '/sources/gallery/' + modes[mode] + '/' + (imageCounter + 1) + '.png';
    imageContainer.src = '/sources/gallery/' + modes[mode] + '/' + imageCounter + '.png';
}

function onSideButtonClick() {
    sideButtons.map(function (sideButton, index) {
        sideButton.onclick = function () {
            if (index == 0 && imageCounter > 0) {
                imageCounter--;
            }
            if (index == 1 && isReadyToNextImage) {
                imageCounter++;
            }
            console.log(imageCounter);
            if (index == 0 && imageCounter == 0) {
                sideButton.style = sideButtonAnimReverse;
            }
            // if (index == 1 && !isReadyToNextImage) {
            //     sideButton.style = sideButtonAnimReverse;
            // }
            loadImage();
            pictureLimiter();
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
    if (!isReadyToNextImage) {
        // sideButtons[1].style = defaultSideButton;
        sideButtons[1].style = sideButtonAnimReverse;
        applyHoverStyles(sideButtons[1], defaultSideButton, defaultSideButton);

    }
    else {
        // sideButtons[1].style = 'opacity: 100%; cursor: pointer;';
        applyHoverStyles(sideButtons[1], sideButtonAnim, sideButtonAnimReverse);

    }
    


}

// function u(someCounter, someButtons, someButtonBottomBorders) {
//     someButtons.map(function (someButton, index) {

//         if (index != someCounter) {
//             someButton.onmouseout = function () {
//                 someButtonBottomBorders[index].style = aButtonBottomBorderAnimReverse;
//                 console.log(someButtonBottomBorders[index]);
//             };
//             someButton.onmouseover = function () {
//                 someButtonBottomBorders[index].style = aButtonBottomBorderAnim;
//             };

//         }
//         if (index == someCounter) {
//             someButton.onmouseout = function () {
//                 someButtonBottomBorders[index].style = defaultAButtonBorder;
//             };
//             someButton.onmouseover = function () {
//                 someButtonBottomBorders[index].style = defaultAButtonBorder;
//             };
//         }
//     });
// }


function main() {
    img.onload = function () { isReadyToNextImage = true; pictureLimiter(); };
    img.onerror = function () { isReadyToNextImage = false; pictureLimiter(); };
    loadImage();
    updateAButtonsAnim(imageCounter, upButtons);
    updateBottomBordersAnim(imageCounter, upButtons, upButtonBottomBorders);
    onUpButtonClick();
    onSideButtonClick();
    pictureLimiter();
}



main();