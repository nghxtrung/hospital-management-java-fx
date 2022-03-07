package controller;

import javafx.scene.control.*;


public class DataValidation {

    /* NULL FORM VALIDATION
    *  Does not allow input null
    * */
    public  static  boolean textFieldIsNull(TextField textField , Label label , String validationTex){
        boolean isNull = false;
        String validationString = null;
        if (textField.getText().isEmpty()){
            isNull = true;
            validationString = validationTex;
        }

        label.setText(validationString);
        return  isNull;
    }

    /* DATA LENGTH AND NUMERIC FORM VALIDATION */
    public  static  boolean dataLength(TextField textField , Label label , String validationTex,String requireLength){
        boolean isDataLength = false;
        String validationString = null;
        if ( textField.getText() == null){
            isDataLength = true;
            validationString = "Không được để trống";
        }
        else if (!textField.getText().matches("\\b\\w" + "{"+requireLength+"}"+"\\b" )){
            isDataLength = true;
            validationString = "Độ dài tối đa là "+requireLength +" số";
        }else if (!textField.getText().matches("[0-9]+" )){
            isDataLength = true;
            validationString = validationTex;
        }


        label.setText(validationString);
        return  isDataLength;
    }


    /* ALPHET FORM VALIDATION
    *  only allows inputs with alphabet letters days
    *  */
    public  static  boolean textAlphabet(TextField textField , Label label , String validationTex){
        boolean isAlphabet = false;
        String validationString = null;
        if (textField.getText().isEmpty() || textField.getText().length() ==0){
            isAlphabet = true;
            validationString = "Không được để trống";
        }
        else if (!textField.getText().matches("[a-z A-z]+" )){
            isAlphabet = true;
            validationString = validationTex;
        }

        label.setText(validationString);
        return  isAlphabet;
    }


    /* GENDER FOR VALIDATION */
    public  static  boolean checkGender(RadioButton female , RadioButton male, Label label , String validationTex){
        boolean isChecked = false;
        String validationString = null;
        if (!( female.isSelected() | male.isSelected())){
            isChecked = true;
            validationString = validationTex;
        }

        label.setText(validationString);

        return  isChecked;
    }

    /* BIRTHDAY FOR VALIDATION */
    public  static  boolean checkDatePicker(DatePicker datePicker, Label label , String validationTex){
        boolean isPicker = false;
        String validationString = null;
        if (datePicker.getEditor().getText().isEmpty()){
            isPicker = true;
            validationString = validationTex;
        }

        label.setText(validationString);

        return  isPicker;
    }

    /* BLOOD GROUP FOR VALIDATION */
    public  static  boolean checkBloodGroup(ChoiceBox choiceBox, Label label , String validationTex){
        boolean isBlood = false;
        String validationString = null;
        int index = choiceBox.getSelectionModel().getSelectedIndex();
        if (index < 0){
            isBlood = true;
            validationString = validationTex;
        }

        label.setText(validationString);

        return  isBlood;
    }



}
