

package mediamanager.gui;

import java.io.File;
import javax.swing.filechooser.*;

public class VideoFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = Utils.getExtension(f);
        if (extension != null) {
            return extension.equals(Utils.mp4) ||
                    extension.equals(Utils.avi) ||
                    extension.equals(Utils.mkv) ||
                    extension.equals(Utils.mpg) ||
                    extension.equals(Utils.mpeg) ||
                    extension.equals(Utils.wmv) ||
                    extension.equals(Utils.rm);
        }

        return false;
    }

    //The description of this filter
    @Override
    public String getDescription() {
        return "Just Videos";
    }
}
