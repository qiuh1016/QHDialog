package com.qiuhong.qhdialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by qiuhong on 9/4/16.
 */

public class QHDialog extends Dialog {

    public QHDialog(Context context) {
        super(context);
    }

    public QHDialog(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder {
        private Context context;
        private String title;
        private String message;
        private String positiveButtonText;
        private String negativeButtonText;
        private boolean mCancelable = true;

        private View contentView;
        private OnClickListener positiveButtonClickListener;
        private OnClickListener negativeButtonClickListener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        /**
         * Set the Dialog message from resource
         *
         * @param message
         * @return
         */
        public Builder setMessage(int message) {
            this.message = (String) context.getText(message);
            return this;
        }

        /**
         * Set the Dialog title from resource
         *
         * @param title
         * @return
         */
        public Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }

        /**
         * Set the Dialog title from String
         *
         * @param title
         * @return
         */

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.mCancelable = cancelable;
            return this;
        }

        /**
         * Set the positive button resource and it's listener
         *
         * @param positiveButtonText
         * @return
         */
        public Builder setPositiveButton(int positiveButtonText,
                                         OnClickListener listener) {
            this.positiveButtonText = (String) context
                    .getText(positiveButtonText);
            if (listener == null) {
                this.positiveButtonClickListener = new OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                };
            } else {
                this.positiveButtonClickListener = listener;
            }
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText,
                                         OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
//            if (listener == null) {
//                this.positiveButtonClickListener = new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                };
//            } else {
//                this.positiveButtonClickListener = listener;
//            }
            return this;
        }

        public Builder setNegativeButton(int negativeButtonText,
                                         OnClickListener listener) {
            this.negativeButtonText = (String) context
                    .getText(negativeButtonText);
            if (listener == null) {
                this.negativeButtonClickListener = new OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                };
            } else {
                this.negativeButtonClickListener = listener;
            }


            return this;
        }

        public Builder setNegativeButton(String negativeButtonText,
                                         OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            if (listener == null) {
                this.negativeButtonClickListener = new OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                };
            } else {
                this.negativeButtonClickListener = listener;
            }
            return this;
        }

        public QHDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final QHDialog dialog = new QHDialog(context,R.style.myDialogActivityStyle);
            View layout = inflater.inflate(R.layout.dialog, null);
            dialog.addContentView(layout, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            // set the dialog title
            NavigationView navigationView = (NavigationView) layout.findViewById(R.id.nav_main_in_dialog);
            navigationView.setBackgroundResource(R.drawable.top_select);
            navigationView.setTitle(title);
            navigationView.setClickCallback(new NavigationView.ClickCallback() {
                @Override
                public void onRightClick() {
                    Log.i("main","点击了右侧按钮!");
                }

                @Override
                public void onBackClick() {
                    Log.i("main","点击了左侧按钮!");
                }
            });

            /**
             * 设置宽度
             */
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            int width = wm.getDefaultDisplay().getWidth();
            LinearLayout content = (LinearLayout) layout.findViewById(R.id.content_in_dialog);
            int dialogWidth = DensityUtil.px2dip(context, width * 4 / 5);
            if (dialogWidth > 320) dialogWidth = 320;
            content.setLayoutParams(new LinearLayout.LayoutParams(DensityUtil.dip2px(context, dialogWidth) , ViewGroup.LayoutParams.WRAP_CONTENT));

            // set the confirm button
            if (positiveButtonText != null) {
                ((Button) layout.findViewById(R.id.left_button_in_dialog))
                        .setText(positiveButtonText);
                if (positiveButtonClickListener != null) {
                    ((Button) layout.findViewById(R.id.left_button_in_dialog))
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    positiveButtonClickListener.onClick(dialog,
                                            DialogInterface.BUTTON_POSITIVE);
                                }
                            });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.left_button_in_dialog).setVisibility(
                        View.GONE);
            }
            // set the cancel button
            if (negativeButtonText != null) {
                ((Button) layout.findViewById(R.id.right_button_in_dialog))
                        .setText(negativeButtonText);
                if (negativeButtonClickListener != null) {
                    ((Button) layout.findViewById(R.id.right_button_in_dialog))
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    negativeButtonClickListener.onClick(dialog,
                                            DialogInterface.BUTTON_NEGATIVE);
                                }
                            });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.right_button_in_dialog).setVisibility(
                        View.GONE);
            }
            // set the content message
            if (message != null) {
                ((TextView) layout.findViewById(R.id.textView_in_dialog)).setText(message);
            } else if (contentView != null) {
                // if no message set
                // add the contentView to the dialog body
                ((LinearLayout) layout.findViewById(R.id.content_in_dialog))
                        .removeAllViews();
                ((LinearLayout) layout.findViewById(R.id.content_in_dialog))
                        .addView(contentView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
            }
            dialog.setContentView(layout);
            dialog.setCancelable(mCancelable);
            return dialog;
        }
    }
}


