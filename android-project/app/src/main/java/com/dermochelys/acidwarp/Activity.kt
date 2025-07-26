/* ACID WARP (c)Copyright 1992, 1993 by Noah Spurrier
 * All Rights reserved. Private Proprietary Source Code by Noah Spurrier
 * Ported to Linux by Steven Wills
 * Ported to SDL by Boris Gjenero
 * Ported to Android by Matthew Zavislak
 */

package com.dermochelys.acidwarp
import android.os.Bundle
import org.libsdl.app.SDLActivity

class Activity(): SDLActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sendCommand(COMMAND_CHANGE_WINDOW_STYLE, 1)
        sendCommand(COMMAND_SET_KEEP_SCREEN_ON, 1)
    }
}
