package com.networkIPS.task.ui.activities.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.networkIPS.task.R;
import com.networkIPS.task.helpers.App;
import com.networkIPS.task.ui.dialogs.MessageActionDialog;

@SuppressLint("Registered")
public class LoadingDialogActivity extends AppCompatActivity {

    private MessageActionDialog messageActionDialog;
    private final boolean DEBUG = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void hideActionDialog() {
        runOnUiThread(() -> {
            if (messageActionDialog != null) {
                messageActionDialog.dismissAllowingStateLoss();
                messageActionDialog = null;
            }
        });
    }

    private void showNoConnectionDialog(MessageActionDialog.ActionClick actionClick, final boolean loading) {
        if (messageActionDialog == null && !App.hasNetwork()) {
            messageActionDialog = MessageActionDialog.getInstance(getString(R.string.msg_no_internet), getString(R.string.retry), actionClick);
            messageActionDialog.setCancelable(DEBUG);
        } else if (messageActionDialog == null && App.hasNetwork()) {
            messageActionDialog = MessageActionDialog.getInstance(getString(R.string.connection_error), getString(R.string.retry), actionClick);
            messageActionDialog.setCancelable(true);
        }

        runOnUiThread(
                () -> {
                    try {
                        if (!messageActionDialog.isAdded()) {
                            messageActionDialog.show(getSupportFragmentManager(), null);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (!loading) messageActionDialog.loading(false);
                                }
                            }, 300);
                        } else {
                            messageActionDialog.loading(true);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );

    }

    public void showConnectionLoading(int SWITCH_CASE) {

        switch (SWITCH_CASE) {
            case MessageActionDialog.SHOW: {
                showNoConnectionDialog(null, true);
                break;
            }

            case MessageActionDialog.HIDE: {
                hideActionDialog();
                break;
            }
        }
    }

    public void showConnectionLoading(int SWITCH_CASE, MessageActionDialog.ActionClick actionClick) {

        switch (SWITCH_CASE) {
            case MessageActionDialog.SHOW: {
                showNoConnectionDialog(actionClick, true);
                break;
            }
            case MessageActionDialog.RETRY: {
                try {
                    if (messageActionDialog != null && messageActionDialog.isAdded()) {
                        messageActionDialog.setActionClick(actionClick);
                        messageActionDialog.loading(false);
                    } else {
                        showNoConnectionDialog(actionClick, false);
                    }
                } catch (Exception ignored) {

                }
                break;
            }
            case MessageActionDialog.HIDE: {
                hideActionDialog();
                break;
            }
        }


    }

}
