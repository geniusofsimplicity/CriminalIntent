package com.example.paul.criminalintent;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import java.util.Date;

/**
 * Created by Paul on 19.06.2016.
 */
public class CrimeImageFragment extends DialogFragment {

    private static final String ARG_IMAGE = "image";
    private ImageView mPhotoFullView;

    public static CrimeImageFragment newInstance(String path){
        Bundle args = new Bundle();
        args.putSerializable(ARG_IMAGE, path);

        CrimeImageFragment fragment = new CrimeImageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        String path = getArguments().getString(ARG_IMAGE);
        Bitmap bitmap = PictureUtils.getScaledBitmap(path, getActivity());
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_crime_image, null);
        mPhotoFullView = (ImageView)v.findViewById(R.id.crime_photo);
        mPhotoFullView.setImageBitmap(bitmap);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.crime_photo_title)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(getTargetFragment() == null){
                                    return;
                                }
                                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, null);
                            }
                        })
                .create();
    }
}
