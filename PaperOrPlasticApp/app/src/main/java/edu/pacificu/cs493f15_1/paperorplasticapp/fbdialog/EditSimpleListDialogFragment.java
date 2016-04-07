package edu.pacificu.cs493f15_1.paperorplasticapp.fbdialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import edu.pacificu.cs493f15_1.paperorplasticapp.R;
import edu.pacificu.cs493f15_1.paperorplasticjava.SimpleList;
import edu.pacificu.cs493f15_1.utils.Constants;

/**
 * Created by alco8653 on 4/3/2016.
 */
public abstract class EditSimpleListDialogFragment extends DialogFragment
{
  String mListId, mOwner, mEncodedEmail;
  EditText mEditTextForList;
  int mResource;

  /**
   * Helper method that creates a basic bundle of all of the information needed to change
   * values in a shopping list.
   *
   * @param simpleList
   * @param resource
   * @return
   */
  protected static Bundle newInstanceHelper(SimpleList simpleList, int resource, String listId, String encodedEmail)
  {
    Bundle bundle = new Bundle();
    bundle.putString(Constants.KEY_LIST_ID, listId);
    bundle.putInt(Constants.KEY_LAYOUT_RESOURCE, resource);
    bundle.putString(Constants.KEY_LIST_OWNER, simpleList.getmOwner());
    bundle.putString(Constants.KEY_ENCODED_EMAIL, encodedEmail);
    return bundle;
  }

  /**
   * Initialize instance variables with data from bundle
   */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    mListId = getArguments().getString(Constants.KEY_LIST_ID);
    mResource = getArguments().getInt(Constants.KEY_LAYOUT_RESOURCE);
    mOwner = getArguments().getString(Constants.KEY_LIST_OWNER);
    mEncodedEmail = getArguments().getString(Constants.KEY_ENCODED_EMAIL);
  }

  /**
   * Open the keyboard automatically when the dialog fragment is opened
   */
  @Override
  public void onActivityCreated(Bundle savedInstanceState)
  {
    super.onActivityCreated(savedInstanceState);
    getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
  }

  protected Dialog createDialogHelper(int stringResourceForPositiveButton)
  {
        /* Use the Builder class for convenient dialog construction */
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomTheme_Dialog);
        /* Get the layout inflater */
    LayoutInflater inflater = getActivity().getLayoutInflater();
        /* Inflate the layout, set root ViewGroup to null*/
    View rootView = inflater.inflate(mResource, null);
    mEditTextForList = (EditText) rootView.findViewById(R.id.edit_text_list_dialog);

    /**
     * Call doListEdit() when user taps "Done" keyboard action
     */
    mEditTextForList.setOnEditorActionListener(new TextView.OnEditorActionListener()
    {
      @Override
      public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent)
      {
        if (actionId == EditorInfo.IME_ACTION_DONE || keyEvent.getAction() == KeyEvent.ACTION_DOWN)
        {
          doListEdit();

          /**
           * Close the dialog fragment when done
           */
          EditSimpleListDialogFragment.this.getDialog().cancel();
        }
        return true;
      }
    });
        /* Inflate and set the layout for the dialog */
        /* Pass null as the parent view because its going in the dialog layout */
    builder.setView(rootView)
                /* Add action buttons */
      .setPositiveButton(stringResourceForPositiveButton, new DialogInterface.OnClickListener()
      {
        @Override
        public void onClick(DialogInterface dialog, int id)
        {
          doListEdit();

          /**
           * Close the dialog fragment
           */
          EditSimpleListDialogFragment.this.getDialog().cancel();
        }
      })
      .setNegativeButton("Cancel", new DialogInterface.OnClickListener()
      {
        @Override
        public void onClick(DialogInterface dialog, int id)
        {

          /**
           * Close the dialog fragment
           */
          EditSimpleListDialogFragment.this.getDialog().cancel();
        }
      });

    return builder.create();
  }

  /**
   * Set the EditText text to be the inputted text
   * and put the pointer at the end of the input
   *
   * @param defaultText
   */
  protected void helpSetDefaultValueEditText(String defaultText)
  {
    mEditTextForList.setText(defaultText);
    mEditTextForList.setSelection(defaultText.length());
  }

  /**
   * Method to be overriden with whatever edit is supposed to happen to the list
   */
  protected abstract void doListEdit();

}