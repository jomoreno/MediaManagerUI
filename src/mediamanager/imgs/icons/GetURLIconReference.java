/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mediamanager.imgs.icons;

import java.net.URL;



/**
 *
 * @author Josue
 */
public class GetURLIconReference {
    
    public static URL getIconURL(String iconName)
    {
        URL resourceURL = mediamanager.imgs.icons.GetURLIconReference.class.getResource(iconName);
        return resourceURL;
    }
    
}
