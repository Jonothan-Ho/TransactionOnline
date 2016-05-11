package com.maimaizaixian.transactiononline.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapCommonUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.maimaizaixian.transactiononline.common.Common;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Bimmap Manage Class..
 * <p/>
 * Such as function is some loading pictures
 *
 * @author Administrator
 */
public class BitmapUtil {

    private static BitmapUtil instance;
    private BitmapUtils bitmapUtils;

    public BitmapUtil(Context context) {
        bitmapUtils = new BitmapUtils(context, FileUtil.getCacheImageDirPath(context, Common.CACHE_IMAGE_NAME_DIR), Common.CACHE_IMAGE_MEMORY_SIZE, Common.CACHE_IMAGE_DISK_SIZE);
        //bitmapUtils = new BitmapUtils(context);
        bitmapUtils.configMemoryCacheEnabled(true);
        bitmapUtils.configDiskCacheEnabled(true);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(300);
        //bitmapUtils.configDefaultImageLoadAnimation(alphaAnimation);

        BitmapDisplayConfig bigPicDisplayConfig = new BitmapDisplayConfig();
        bigPicDisplayConfig.setBitmapConfig(Bitmap.Config.RGB_565);
        bigPicDisplayConfig.setBitmapMaxSize(BitmapCommonUtils.getScreenSize(context));
        bigPicDisplayConfig.setAnimation(alphaAnimation);
        bitmapUtils.configDefaultDisplayConfig(bigPicDisplayConfig);

    }

    public static BitmapUtil getInstance(Context context) {

        synchronized (context) {
            if (instance == null) {
                instance = new BitmapUtil(context);
            }
            return instance;
        }
    }

    /**
     * @param imageView
     * @param url
     */
    public void displayImage(ImageView imageView, String url) {
        if (!TextUtils.isEmpty(url)) {
            bitmapUtils.display(imageView, url);
        }
    }


    public void displayImage(ImageView imageView, String url,
                             BitmapLoadCallBack<ImageView> bitmapLoadCallBack) {
        bitmapUtils.display(imageView, url, bitmapLoadCallBack);
    }


    public void clearCache() {
        bitmapUtils.clearCache();
    }

    public static Bitmap convertViewToBitmap(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }


    public static Drawable bitmapToDrawable(Bitmap bitmap) {
        BitmapDrawable drawable = new BitmapDrawable(bitmap);
        return drawable;
    }

    /**
     * @param activity
     * @param uri
     * @return
     * @throws IOException
     */
    public static Bitmap getBitmapFromUri(Activity activity, Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
                activity.getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }

    /**
     * @param filePath
     * @return
     */
    public static Bitmap getBitmapFromFile(String filePath) {

        if (TextUtils.isEmpty(filePath)) {
            throw new RuntimeException("Bitmap file path don't be null");
        }

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, 480, 800);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        options.inPreferredConfig = Bitmap.Config.RGB_565;//default
        options.inPurgeable = true;//
        options.inInputShareable = true;//

        return BitmapFactory.decodeFile(filePath, options);
    }

    public static Bitmap getBitmapFromFile(File cacheFile) {
        return getBitmapFromFile(cacheFile.getAbsolutePath());
    }


    /**
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    private static int calculateInSampleSize(BitmapFactory.Options options,
                                             int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            // Calculate ratios of height and width to requested height and
            // width
            final int heightRatio = Math.round((float) height
                    / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will
            // guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            inSampleSize = heightRatio < widthRatio ? widthRatio : heightRatio;
        }

        return inSampleSize;
    }


    /**
     * @param activity
     * @param uri
     * @return
     * @throws IOException
     */
    public static Bitmap compressBitmapToCacheFile(Activity activity, Uri uri, File cacheFile) throws IOException {
        String filePath = FileUtil.getFilePathFromUri(activity, uri);
        Bitmap bitmap = getBitmapFromFile(filePath);

        //start compress to a cacheFile
        compressBitmap(bitmap, cacheFile, Common.CACHE_BITMAP_MAX_SIZE);

        bitmap = getBitmapFromFile(cacheFile.getAbsolutePath());

        return bitmap;
    }

    public static Bitmap compressFileToBitmap(Activity activity, File cacheFile) throws IOException {
        Bitmap bitmap = getBitmapFromFile(cacheFile);

        //start compress to a cacheFile
        compressBitmap(bitmap, cacheFile, Common.CACHE_BITMAP_MAX_SIZE);

        bitmap = getBitmapFromFile(cacheFile.getAbsolutePath());

        return bitmap;
    }


    /**
     * compress Bitmap
     *
     * @param bitmap
     * @param cacheFile
     * @param maxSize
     */
    public static void compressBitmap(Bitmap bitmap, File cacheFile, int maxSize) {
        if (bitmap == null) {
            throw new RuntimeException("Bitmap Don't be null");
        }

        ByteArrayOutputStream baos = null;
        FileOutputStream fos = null;

        try {
            baos = new ByteArrayOutputStream();
            fos = new FileOutputStream(cacheFile);

            int quality = 90;
            while ((baos.toByteArray().length / 1024) > maxSize) {
                baos.reset();
                quality -= 10;
                bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
            }

            if (baos.toByteArray().length <= 0) {
                bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
            }

            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (!bitmap.isRecycled()) {
                bitmap.recycle();
            }

        }

    }


    /**
     * Through the uri call crop from system
     *
     * @param activity
     * @param uri
     * @param outputFile
     * @param aspectX
     * @param aspectY
     * @param outputX
     * @param outputY
     * @param requestCode
     */
    public static void callCropMethodFromSystem(Activity activity, Uri uri, File outputFile, int aspectX, int aspectY, int outputX, int outputY, int requestCode) {
        Intent intent = new Intent("com.android.camera.action.CROP");//action-crop
        intent.setDataAndType(uri, "image/*");//type
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(outputFile));// output file
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());// output format
        intent.putExtra("crop", true);
        intent.putExtra("aspectX", aspectX);
        intent.putExtra("aspectY", aspectY);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", true);// scale
        intent.putExtra("scaleUpIfNeeded", true);
        intent.putExtra("return-data", false);// no return Thumbnail
        intent.putExtra("noFaceDetection", true);// colse face detection
        activity.startActivityForResult(intent, requestCode);
    }


}
