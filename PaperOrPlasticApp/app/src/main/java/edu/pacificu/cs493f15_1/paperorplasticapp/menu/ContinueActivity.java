/**************************************************************************************************
*   File:     ContinueActivity.java
*   Author:   Abigail Jones
*   Date:     10/28/15
*   Class:    Capstone/Software Engineering
*   Project:  PaperOrPlastic Application
*   Purpose:  This activity will be the activity that is opened when the user signs into the
*             application or when a user presses continue without signing in
***************************************************************************************************/

package edu.pacificu.cs493f15_1.paperorplasticapp.menu;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.pacificu.cs493f15_1.paperorplasticapp.R;
import edu.pacificu.cs493f15_1.paperorplasticapp.coupons.CouponsActivity;
import edu.pacificu.cs493f15_1.paperorplasticapp.groceryList.GroceryListActivity;
import edu.pacificu.cs493f15_1.paperorplasticapp.kitchenInventory.KitchenInventoryActivity;
import edu.pacificu.cs493f15_1.paperorplasticapp.nutrition.NutritionActivity;
import edu.pacificu.cs493f15_1.paperorplasticapp.recipe.RecipesActivity;

/***************************************************************************************************
 *   Class:         ContinueActivity
 *   Description:   Creates ContinueActivity class that controls what occurs when the user
 *                  reaches the continue page. Controls the look of the continue page,
 *                  initializes buttons found on this page,
 *                  creates intents that take users to those specific pages.
 *   Parameters:    N/A
 *   Returned:      N/A
 **************************************************************************************************/
public class ContinueActivity extends Activity implements View.OnClickListener
{
    static final int G_LIST = 0;
    static final int K_LIST = 1;
    static final int COUPONS = 2;
    static final int NUTRITION = 3;
    static final int RECIPES = 4;
    static final int SETTINGS = 5;
    static final int ABOUT = 6;
    static final int NUM_BUTTONS = 5;

    public static List<Button> buttons;

    private static final int[] BUTTON_IDS = {R.id.bContGList,   R.id.bContKInv,
                                             R.id.bContCoupons, R.id.bContNutrition,
                                             R.id.bContRecipes, R.id.bContSettings,
                                             R.id.bContAbout};

    //Used to change fonts
    private TextView titleText;

    //Controls the transparency of buttons
    final int ALPHA_SETTING = 35;


    /***********************************************************************************************
    *   Method:        onCreate
    *   Description:   is called when the activity is created. Sets the content view and initializes
    *                  our buttons and text fields
    *   Parameters:    savedInstanceState
    *   Returned:      N/A
    ***********************************************************************************************/
    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continue);

        //Initialize text fields
        titleText = (TextView) findViewById (R.id.SettingsPageTitleText);
        Typeface laneUpperFont = Typeface.createFromAsset (getAssets (), "fonts/laneWUnderLine.ttf");
        Typeface laneNarrowFont = Typeface.createFromAsset (getAssets (), "fonts/LANENAR.ttf");
        titleText.setTypeface (laneUpperFont);

        //Create and initialize buttons
        buttons = new ArrayList<Button> (BUTTON_IDS.length);

        for (int id: BUTTON_IDS)
        {
            Button button = (Button) findViewById(id);
            button.setOnClickListener(this);
            button.getBackground ().setAlpha(ALPHA_SETTING);
            button.setTypeface (laneNarrowFont, Typeface.BOLD);
            buttons.add (button);
        }

        loadSavedPreferences ();
    }


    /***********************************************************************************************
    *   Method:        onSaveInstanceState
    *   Description:   Called to retrieve per-instance state from an activity before being killed
    *                  so that the state can be restored in onCreate
    *   Parameters:    savedInstanceState
    *   Returned:      N/A
    ***********************************************************************************************/
    public void onSaveInstanceState (Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onResume ()
    {
        super.onResume();
    }

    /***********************************************************************************************
    *   Method:      onClick
    *   Description: Called when a click has been captured.
    *                If the about, settings, kitchen lists, grocery list, nutrition, or coupon
    *                buttons are selected than a new intent is created and the specified activity
    *                is started
    *   Parameters:  view - the view that has been clicked
    *   Returned:    N/A
    ***********************************************************************************************/
    public void onClick (View view)
    {
        Intent intent;

        if (buttons.get (G_LIST) == view)
        {
            //will start a new activity using the intents
            intent = new Intent (this, GroceryListActivity.class);
            startActivity (intent);
        }
        else if (buttons.get (K_LIST) == view)
        {
            //will start a new activity using the intents
            intent = new Intent (this, KitchenInventoryActivity.class);
            startActivity (intent);
        }
        else if (buttons.get (COUPONS) == view)
        {
            //will start a new activity using the intents
            intent = new Intent (this, CouponsActivity.class);
            startActivity (intent);
        }
        else if (buttons.get (NUTRITION) == view)
        {
            //will start a new activity using the intents
            intent = new Intent (this, NutritionActivity.class);
            startActivity (intent);
        }
        else if (buttons.get (RECIPES) == view)
        {
            //will start a new activity using the intents
            intent = new Intent (this, RecipesActivity.class);
            startActivity (intent);
        }
        else if (buttons.get (SETTINGS) == view)
        {
            //will start a new activity using the intents
            intent = new Intent (this, SettingsActivity.class);
            startActivity (intent);
        }
        else if (buttons.get (ABOUT) == view)
        {
            //will start a new activity using the intents
            intent = new Intent (this, AboutActivity.class);
            startActivity (intent);
        }
    }

    public void loadSavedPreferences ()
    {
        for (int index = 0; index < NUM_BUTTONS; index++)
        {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            boolean switchIsChecked = sharedPreferences.getBoolean(SettingsActivity.SWITCH_PREF_KEYS[index], true);

            if (switchIsChecked)
            {
                buttons.get (index).setVisibility(View.VISIBLE);

            } else
            {
                buttons.get (index).setVisibility(View.GONE);
            }
        }
    }
}

