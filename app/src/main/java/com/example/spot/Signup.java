package com.example.spot;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity implements View.OnClickListener {
    ImageView img;
    EditText sname, phone, place, post, pincode, city, district, uname, psw;
    Button bt;

    final int CAMERA_PIC_REQUEST = 0, GALLERY_CODE = 201;
    private Uri mImageCaptureUri;
    public static String encodedImage = "";
    public static byte[] byteArray;
    Bitmap bitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        img = findViewById(R.id.imageView);
        sname = findViewById(R.id.editTextTextPersonName3);
        phone = findViewById(R.id.editTextTextPersonName9);
        place = findViewById(R.id.editTextTextPersonName4);
        post = findViewById(R.id.editTextTextPersonName5);
        pincode = findViewById(R.id.editTextTextPersonName6);
        city = findViewById(R.id.editTextTextPersonName7);
        district = findViewById(R.id.editTextTextPersonName8);
        uname = findViewById(R.id.editTextTextPersonName10);
        psw = findViewById(R.id.editTextTextPassword2);

        bt = findViewById(R.id.button3);
        bt.setOnClickListener(this);
        img.setOnClickListener(this);

        // Request permissions if needed
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions();
        }
    }

    private void requestPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
    }

    @Override
    public void onClick(View view) {
        if (view == img) {
            selectImageOption();
        } else if (view == bt) {
            handleSignup();
        }
    }

    private void handleSignup() {
        if (isValidInput()) {
            SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String url = sh.getString("url", "") + "passenger_registration";
            Toast.makeText(getApplicationContext(), url, Toast.LENGTH_SHORT).show();

            VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, url,
                    new Response.Listener<NetworkResponse>() {
                        @Override
                        public void onResponse(NetworkResponse response) {
                            try {
                                JSONObject obj = new JSONObject(new String(response.data));
                                if (obj.getString("status").equals("ok")) {
                                    Toast.makeText(getApplicationContext(), "Registration success", Toast.LENGTH_SHORT).show();
                                    SharedPreferences.Editor ed = sh.edit();
                                    ed.putString("lid", obj.getString("lid"));
                                    ed.putString("pid", obj.getString("pid"));
                                    ed.apply();
                                    startActivity(new Intent(getApplicationContext(), Login.class));
                                } else {
                                    Toast.makeText(getApplicationContext(), "Passenger registration Successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), Login.class));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("pname", sname.getText().toString().trim());
                    params.put("phone", phone.getText().toString().trim());
                    params.put("place", place.getText().toString().trim());
                    params.put("post", post.getText().toString().trim());
                    params.put("pincode", pincode.getText().toString().trim());
                    params.put("city", city.getText().toString().trim());
                    params.put("district", district.getText().toString().trim());
                    params.put("username", uname.getText().toString().trim());
                    params.put("password", psw.getText().toString().trim());
                    return params;
                }

                @Override
                protected Map<String, DataPart> getByteData() {
                    Map<String, DataPart> params = new HashMap<>();
                    long imagename = System.currentTimeMillis();
                    params.put("photo", new DataPart(imagename + ".png", getFileDataFromDrawable(bitmap)));
                    return params;
                }
            };

            Volley.newRequestQueue(this).add(volleyMultipartRequest);
        } else {
            Toast.makeText(getApplicationContext(), "Please fill in all fields correctly", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValidInput() {
        boolean isValid = true;

        String sname1 = sname.getText().toString().trim();
        String phone1 = phone.getText().toString().trim();
        String place1 = place.getText().toString().trim();
        String post1 = post.getText().toString().trim();
        String city1 = city.getText().toString().trim();
        String district1 = district.getText().toString().trim();
        String uname1 = uname.getText().toString().trim();
        String psw1 = psw.getText().toString().trim();
        String pincode1 = pincode.getText().toString().trim();

        if (sname1.isEmpty()) {
            sname.setError("Please enter your first name");
            isValid = false;
        }
        if (phone1.isEmpty()) {
            phone.setError("Please enter your phone number");
            isValid = false;
        } else if (!phone1.matches("^[+]?[0-9]{10,13}$")) {
            phone.setError("Please enter a valid phone number");
            isValid = false;
        }
        if (place1.isEmpty()) {
            place.setError("Please enter your place");
            isValid = false;
        }
        if (post1.isEmpty()) {
            post.setError("Please enter post");
            isValid = false;
        }
        if (pincode1.isEmpty()) {
            pincode.setError("Please enter pincode");
            isValid = false;
        } else if (!pincode1.matches("^[0-9]{6}$")) {
            pincode.setError("Please enter a valid pincode");
            isValid = false;
        }
        if (city1.isEmpty()) {
            city.setError("Please enter City");
            isValid = false;
        }
        if (district1.isEmpty()) {
            district.setError("Please enter district");
            isValid = false;
        }
        if (uname1.isEmpty()) {
            uname.setError("Please enter a username");
            isValid = false;
        }
        if (psw1.isEmpty()) {
            psw.setError("Please enter a password");
            isValid = false;
        }
        if (encodedImage.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please select an image", Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        return isValid;
    }

    @TargetApi(Build.VERSION_CODES.FROYO)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_CODE && resultCode == RESULT_OK && data != null) {
            handleGalleryResult(data);
        } else if (requestCode == CAMERA_PIC_REQUEST && resultCode == RESULT_OK) {
            handleCameraResult(data);
        }
    }

    private void handleGalleryResult(Intent data) {
        mImageCaptureUri = data.getData();
        Log.d("File Uri", "File Uri: " + mImageCaptureUri.toString());

        try {
            String path = FileUtils.getPath(this, mImageCaptureUri);
            Toast.makeText(getApplicationContext(), "Path: " + path, Toast.LENGTH_LONG).show();

            File file = new File(path);
            InputStream inputStream = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[(int) file.length()];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            inputStream.close();

            byteArray = bos.toByteArray();
            bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            img.setImageBitmap(bitmap);

            encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error: " + e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    private void handleCameraResult(Intent data) {
        try {
            bitmap = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            img.setImageBitmap(bitmap);

            byteArray = baos.toByteArray();
            encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private void selectImageOption() {
        final CharSequence[] items = {"Capture Photo", "Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Signup.this);
        builder.setTitle("Take Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Capture Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, CAMERA_PIC_REQUEST);
                } else if (items[item].equals("Choose from Gallery")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, GALLERY_CODE);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
}
