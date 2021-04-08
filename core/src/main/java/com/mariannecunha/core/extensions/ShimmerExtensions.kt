package com.mariannecunha.core.extensions

import com.facebook.shimmer.ShimmerFrameLayout

fun ShimmerFrameLayout.show() {
    visibility = android.view.View.VISIBLE
    startShimmer()
}
