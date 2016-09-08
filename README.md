# qhdialog



To get a Git project into your build:

Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

    allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}

Step 2. Add the dependency

	dependencies {
	        compile 'com.github.qiuh1016:qhdialog:1.0.0'
	}



Demo:

        QHDialog.Builder builder = new QHDialog.Builder(this);
        builder.setTitle("Title");
        builder.setMessage("message");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); //Don't forget to add this line
                }
            });
        builder.setNegativeButton("Cancel", null);
        builder.create().show();
