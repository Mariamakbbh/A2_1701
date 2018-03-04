package drawshape.custom;

import javafx.scene.control.TextField;

public class IntegerTextField extends TextField{

	public IntegerTextField(){
		this.setPromptText("Please type only integer values between 30 and 90");
	}
	
	@Override
	public void replaceText(int i, int i1, String string){
		if(string.matches("[0-9]") || string.isEmpty()) {
			super.replaceText(i, i1, string);
		}
	}

	@Override
	public void replaceSelection(String string) {
		super.replaceSelection(string);
	}
}
