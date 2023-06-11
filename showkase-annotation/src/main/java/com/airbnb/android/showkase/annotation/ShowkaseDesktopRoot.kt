package com.airbnb.android.showkase.annotation


/**
 * Used to annotate the [ShowkaseRootModule] implementation class. This is needed to let Showkase
 * know more about the module that is going to be the root module for aggregating all the Showkase
 * supported UI elements across all the different modules(if you are using a multi-module
 * project). If you are only using a single module in your project, add it to that module. You
 * are allowed to have only one @ShowkaseRoot per module.
 *
 * // TODO: Add KMM docs
 */
@MustBeDocumented
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.CLASS)
annotation class ShowkaseDesktopRoot
