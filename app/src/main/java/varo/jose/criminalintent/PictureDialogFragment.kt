package varo.jose.criminalintent

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Point
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import java.io.File

class PictureDialogFragment(
    val photoFile: File
) : DialogFragment() {

    private lateinit var imageView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.view_picture, container, false)
        imageView = view.findViewById(R.id.imageView) as ImageView

        val image = getScaledBitmap(photoFile.path, requireActivity())
        imageView.setImageBitmap(image)

        return view
    }

    fun getScaledBitmap(path: String, activity: Activity): Bitmap {
        val size = Point()
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            val display = activity.display
            display?.getRealSize(size)
        } else {
            @Suppress("DEPRECATION")
            activity.windowManager.defaultDisplay.getSize(size)
        }
        return getScaledBitmap(path, size.x, size.y)
    }
}