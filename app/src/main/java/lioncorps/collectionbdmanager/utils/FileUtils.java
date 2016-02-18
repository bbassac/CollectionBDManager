package lioncorps.collectionbdmanager.utils;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


public class FileUtils {
    public static String UPDATED_JSON = "listing2";

    private static String offlineModeFile = "oldjson.txt";
    private static String crlf = System.getProperty("line.separator");
    private static String directoryDownloads = Environment.DIRECTORY_DCIM;

    public static boolean saveContent(Activity activity, String content) {
        return saveContentToFile(activity, content, offlineModeFile);
    }

    public static boolean saveContentToFile(Activity activity, String content, String fileName) {
        try {
            File f = new File(Environment.getExternalStoragePublicDirectory(
                    directoryDownloads), fileName);
            FileOutputStream newS = new FileOutputStream(f, false);

            Writer out = new OutputStreamWriter(newS);
            out.write(content);
            out.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static String buildString(BufferedReader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append(crlf);
        }
        return sb.toString();
    }

    public static String getBuildDate(Activity activity) {
        String s = "";
        try {
            ApplicationInfo ai = activity.getPackageManager().getApplicationInfo(activity.getPackageName(), 0);
            ZipFile zf = new ZipFile(ai.sourceDir);
            ZipEntry ze = zf.getEntry("META-INF/MANIFEST.MF");
            long time = ze.getTime();
            SimpleDateFormat formatter = (SimpleDateFormat) SimpleDateFormat.getInstance();
            formatter.setTimeZone(TimeZone.getTimeZone("gmt"));
            s = formatter.format(new java.util.Date(time));
            zf.close();
        } catch (Exception e) {
        }
        return s;
    }


}
