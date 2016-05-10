

package mediamanager.gui;

import java.io.File;
import javax.swing.filechooser.*;

public class FileFilterText extends FileFilter {

    //Accept all directories and all gif, jpg, tiff, or png files.
    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = Utils.getExtension(f);
        if (extension != null) {
            if (extension.equals(Utils.txt) ||
                extension.equals(Utils.csv)) {
                    return true;
            } else {
                return false;
            }
        }

        return false;
    }

    //The description of this filter
    @Override
    public String getDescription() {
        return "Just text";
    }
}
