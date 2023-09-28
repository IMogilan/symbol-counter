let inputTextArea = $("#input-textarea");
let outputTextArea = $("#output-textarea");
let runButton = $("#run-button");
let resetButton = $("#reset-button");
let alertDiv = $("#alert-div");
let alertTextElement = $("#alert-text");
let alertButtonClose = $("#alert-button-close");

alertDiv.hide();

runButton.on("click", function (){
    resetAlertAndOutputArea();
    let enteredTextDto = {
        value : inputTextArea.val()
    };
    countFrequencyOfCharacters(enteredTextDto);
})

function countFrequencyOfCharacters(enteredTextDto){
    $.ajax(
        {
            url : "/character/countFrequency",
            method: "POST",
            data: JSON.stringify(enteredTextDto),
            contentType: "application/json; charset=utf-8",
            success: function (response){
                outputTextArea.val(response.characterFrequency);
            },
            error: function (xhr, status, error){
                let errorMessage = xhr.responseJSON.value;
                alertTextElement.html(errorMessage);
                alertDiv.show();
            }
        }
    )
}

resetButton.on("click", function (){
    resetAll();
})
alertButtonClose.click(function () {
        resetAlert();
    }
);


function resetAlert() {
    alertTextElement.empty();
    alertDiv.hide();
}

function resetAlertAndOutputArea(){
    outputTextArea.val(outputTextArea.data("default"));
    resetAlert();
}

function resetAll(){
    inputTextArea.val('');
    resetAlertAndOutputArea();
}