/*
 * Copyright (c) 2021 SoundWave Project
 * Accent.kt is part of SoundWave.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
 
package com.itech.soundwave.ui.accent

import android.os.Build
import com.itech.soundwave.R
import timber.log.Timber as L

private val accentNames =
    intArrayOf(
        R.string.clr_red,
        R.string.clr_pink,
        R.string.clr_purple,
        R.string.clr_deep_purple,
        R.string.clr_indigo,
        R.string.clr_blue,
        R.string.clr_deep_blue,
        R.string.clr_cyan,
        R.string.clr_teal,
        R.string.clr_green,
        R.string.clr_deep_green,
        R.string.clr_lime,
        R.string.clr_yellow,
        R.string.clr_orange,
        R.string.clr_brown,
        R.string.clr_grey,
        R.string.clr_dynamic,
    )

private val accentThemes =
    intArrayOf(
        R.style.Theme_SoundWave_Red,
        R.style.Theme_SoundWave_Pink,
        R.style.Theme_SoundWave_Purple,
        R.style.Theme_SoundWave_DeepPurple,
        R.style.Theme_SoundWave_Indigo,
        R.style.Theme_SoundWave_Blue,
        R.style.Theme_SoundWave_DeepBlue,
        R.style.Theme_SoundWave_Cyan,
        R.style.Theme_SoundWave_Teal,
        R.style.Theme_SoundWave_Green,
        R.style.Theme_SoundWave_DeepGreen,
        R.style.Theme_SoundWave_Lime,
        R.style.Theme_SoundWave_Yellow,
        R.style.Theme_SoundWave_Orange,
        R.style.Theme_SoundWave_Brown,
        R.style.Theme_SoundWave_Grey,
        R.style.Theme_SoundWave_App, // Dynamic colors are on the base theme
    )

private val accentBlackThemes =
    intArrayOf(
        R.style.Theme_SoundWave_Red_Black,
        R.style.Theme_SoundWave_Pink_Black,
        R.style.Theme_SoundWave_Purple_Black,
        R.style.Theme_SoundWave_DeepPurple_Black,
        R.style.Theme_SoundWave_Indigo_Black,
        R.style.Theme_SoundWave_Blue_Black,
        R.style.Theme_SoundWave_DeepBlue_Black,
        R.style.Theme_SoundWave_Cyan_Black,
        R.style.Theme_SoundWave_Teal_Black,
        R.style.Theme_SoundWave_Green_Black,
        R.style.Theme_SoundWave_DeepGreen_Black,
        R.style.Theme_SoundWave_Lime_Black,
        R.style.Theme_SoundWave_Yellow_Black,
        R.style.Theme_SoundWave_Orange_Black,
        R.style.Theme_SoundWave_Brown_Black,
        R.style.Theme_SoundWave_Grey_Black,
        R.style.Theme_SoundWave_Black, // Dynamic colors are on the base theme
    )

private val accentPrimaryColors =
    intArrayOf(
        R.color.red_primary,
        R.color.pink_primary,
        R.color.purple_primary,
        R.color.deep_purple_primary,
        R.color.indigo_primary,
        R.color.blue_primary,
        R.color.deep_blue_primary,
        R.color.cyan_primary,
        R.color.teal_primary,
        R.color.green_primary,
        R.color.deep_green_primary,
        R.color.lime_primary,
        R.color.yellow_primary,
        R.color.orange_primary,
        R.color.brown_primary,
        R.color.grey_primary,
        R.color.dynamic_primary,
    )

/**
 * The data object for a colored theme to use in the UI. This can be nominally used to gleam some
 * attributes about a given color scheme, but this is not recommended. Attributes are the better
 * option in nearly all cases.
 *
 * @param index The unique number for this particular accent.
 * @author iTECH
 */
class Accent private constructor(val index: Int) {
    /** The name of this [Accent]. */
    val name: Int
        get() = accentNames[index]

    /** The theme resource for this accent. */
    val theme: Int
        get() = accentThemes[index]

    /**
     * The black theme resource for this accent. Identical to [theme], but with a black background.
     */
    val blackTheme: Int
        get() = accentBlackThemes[index]

    /** The accent's primary color. */
    val primary: Int
        get() = accentPrimaryColors[index]

    override fun equals(other: Any?) = other is Accent && index == other.index

    override fun hashCode() = index.hashCode()

    companion object {
        /**
         * Create a new instance.
         *
         * @param index The unique number for this particular accent.
         * @return A new [Accent] with the specified [index]. If [index] is not within the range of
         *   valid accents, [index] will be [DEFAULT] instead.
         */
        fun from(index: Int): Accent {
            if (index !in 0 until MAX) {
                L.w("Accent is out of bounds [idx: $index]")
                return Accent(DEFAULT)
            }
            return Accent(index)
        }

        /** The default accent. */
        val DEFAULT =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                // Use dynamic coloring on devices that support it.
                accentThemes.lastIndex
            } else {
                // Use blue everywhere else.
                5
            }

        /** The amount of valid accents. */
        val MAX =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                accentThemes.size
            } else {
                // Disable the option for a dynamic accent on unsupported devices.
                accentThemes.size - 1
            }
    }
}
            } else {
                // Disable the option for a dynamic accent on unsupported devices.
                accentThemes.size - 1
            }
    }
}
