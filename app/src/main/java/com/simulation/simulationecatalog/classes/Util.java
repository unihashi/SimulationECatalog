package com.simulation.simulationecatalog.classes;

import android.graphics.Typeface;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.util.Patterns;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.github.inflationx.calligraphy3.CalligraphyTypefaceSpan;

public class Util {
    public static boolean isJSONValid(String test) {

        if(test != null && test.trim() != ""){
            try {
                new JSONObject(test);
            } catch (JSONException ex) {
                // edited, to include @Arthur's comment
                // e.g. in case JSONArray is valid as well...
                try {
                    new JSONArray(test);
                } catch (JSONException ex1) {
                    return false;
                }
            }
            return true;
        }else{
            return false;
        }

    }

    public static InputFilter[] usernameFilter(){
        return new InputFilter[]{
                new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence cs, int i, int i1, Spanned spanned, int i2, int i3) {
                        if(cs.equals("")){ // for backspace
                            return cs;
                        }
                        if(cs.toString().matches("^[a-zA-Z0-9]+$")){
                            return cs;
                        }
                        return "";
                    }
                }
        };
    }

    public static InputFilter[] passwordFilter(){
        return new InputFilter[]{
                new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence cs, int i, int i1, Spanned spanned, int i2, int i3) {
                        if(cs.equals("")){ // for backspace
                            return cs;
                        }
                        if(cs.toString().matches("^[a-zA-Z0-9!@#$&()%^*_=\\\\|\\\\\\[\\]{}/<>?:;\\-`.+,/]*$")){
                            return cs;
                        }
                        return "";
                    }
                }
        };
    }

    public static boolean isMatchText(String txt, String txtMatch){
        return txt.equals(txtMatch);
    }

    public static boolean isValidEmail(String email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isStringEmpty(String s){
        if(s == null){
            return true;
        }else{
            return s.trim().isEmpty();
        }
    }

    public static SpannableString setErrorFont(Typeface typeface, CharSequence errorText){
        CalligraphyTypefaceSpan typefaceSpan = new CalligraphyTypefaceSpan(typeface);
        SpannableString spannable = new SpannableString(errorText);
        spannable.setSpan(typefaceSpan, 0, errorText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannable;
    }
}