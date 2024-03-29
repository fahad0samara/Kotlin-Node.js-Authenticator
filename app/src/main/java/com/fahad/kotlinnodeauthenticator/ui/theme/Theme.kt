package com.fahad.kotlinnodeauthenticator.ui.theme


import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.fahad.kotlinnodeauthenticator.ui.MainActivity

private val LightColors = lightColorScheme(
  primary = md_theme_light_primary,
  onPrimary = md_theme_light_onPrimary,
  primaryContainer = md_theme_light_primaryContainer,
  onPrimaryContainer = md_theme_light_onPrimaryContainer,
  secondary = md_theme_light_secondary,
  onSecondary = md_theme_light_onSecondary,
  secondaryContainer = md_theme_light_secondaryContainer,
  onSecondaryContainer = md_theme_light_onSecondaryContainer,
  tertiary = md_theme_light_tertiary,
  onTertiary = md_theme_light_onTertiary,
  tertiaryContainer = md_theme_light_tertiaryContainer,
  onTertiaryContainer = md_theme_light_onTertiaryContainer,
  error = md_theme_light_error,
  errorContainer = md_theme_light_errorContainer,
  onError = md_theme_light_onError,
  onErrorContainer = md_theme_light_onErrorContainer,
  background = md_theme_light_background,
  onBackground = md_theme_light_onBackground,
  surface = md_theme_light_surface,
  onSurface = md_theme_light_onSurface,
  surfaceVariant = md_theme_light_surfaceVariant,
  onSurfaceVariant = md_theme_light_onSurfaceVariant,
  outline = md_theme_light_outline,
  inverseOnSurface = md_theme_light_inverseOnSurface,
  inverseSurface = md_theme_light_inverseSurface,
  inversePrimary = md_theme_light_inversePrimary,
  surfaceTint = md_theme_light_surfaceTint,
  outlineVariant = md_theme_light_outlineVariant,
  scrim = md_theme_light_scrim,
)


private val DarkColors = darkColorScheme(
  primary = md_theme_dark_primary,
  onPrimary = md_theme_dark_onPrimary,
  primaryContainer = md_theme_dark_primaryContainer,
  onPrimaryContainer = md_theme_dark_onPrimaryContainer,
  secondary = md_theme_dark_secondary,
  onSecondary = md_theme_dark_onSecondary,
  secondaryContainer = md_theme_dark_secondaryContainer,
  onSecondaryContainer = md_theme_dark_onSecondaryContainer,
  tertiary = md_theme_dark_tertiary,
  onTertiary = md_theme_dark_onTertiary,
  tertiaryContainer = md_theme_dark_tertiaryContainer,
  onTertiaryContainer = md_theme_dark_onTertiaryContainer,
  error = md_theme_dark_error,
  errorContainer = md_theme_dark_errorContainer,
  onError = md_theme_dark_onError,
  onErrorContainer = md_theme_dark_onErrorContainer,
  background = md_theme_dark_background,
  onBackground = md_theme_dark_onBackground,
  surface = md_theme_dark_surface,
  onSurface = md_theme_dark_onSurface,
  surfaceVariant = md_theme_dark_surfaceVariant,
  onSurfaceVariant = md_theme_dark_onSurfaceVariant,
  outline = md_theme_dark_outline,
  inverseOnSurface = md_theme_dark_inverseOnSurface,
  inverseSurface = md_theme_dark_inverseSurface,
  inversePrimary = md_theme_dark_inversePrimary,
  surfaceTint = md_theme_dark_surfaceTint,
  outlineVariant = md_theme_dark_outlineVariant,
  scrim = md_theme_dark_scrim,
)


@Composable
fun KotlinNodeAuthenticatorTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  // Dynamic color is available on Android 12+
  dynamicColor: Boolean = true,
  activity: Activity = LocalContext.current as MainActivity,
  content: @Composable () -> Unit,
) {
  // Determine color scheme based on conditions
  val colorScheme = when {
    dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
      val context = LocalContext.current
      if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    }
    darkTheme -> DarkColors
    else -> LightColors
  }

  val view = LocalView.current
  // Apply system UI changes only when not in edit mode
  if (!view.isInEditMode) {
    SideEffect {
      val window = (view.context as Activity).window
      window.statusBarColor = Color.Transparent.toArgb()
      WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
    }
  }

  // Calculate window size class and screen configuration

  val config = LocalConfiguration.current

  // Set typography and dimensions based on screen size
  var typography = CompactTypography
  var appDimens = CompactDimens

  when {
    config.screenHeightDp <= 600 && config.screenWidthDp <= 400 -> {
      // Very Small
      appDimens = VerySmallDimens
      typography = VerySmallTypography
    }
    config.screenHeightDp <= 732 && config.screenWidthDp <= 412 -> {
      // Compact
      appDimens = CompactDimens
      typography = CompactTypography
    }
    config.screenWidthDp < 599 -> {
      // Compact Medium
      appDimens = CompactMediumDimens
      typography = CompactMediumTypography
    }
    config.screenWidthDp >= 599 && config.screenWidthDp < 960 -> {
      // Medium
      appDimens = MediumDimens
      typography = MediumDimensTypography
    }
    config.screenWidthDp >= 960 && config.screenWidthDp < 1200 -> {
      // Large
      appDimens = LargeDimens
      typography = LargeTypography
    }
    else -> {
      // Expanded
      appDimens = ExpandedDimens
      typography = ExpandedTypography
    }
  }

  // Apply the custom app dimensions
  ProvideAppUtils(appDimens = appDimens) {
    // Apply MaterialTheme with the determined color scheme and typography
    MaterialTheme(
      colorScheme = colorScheme,
      typography = typography,
      content = content
    )
  }
}

// Accessor for app dimensions
val dimens
  @Composable
  get() = LocalAppDimens.current




