    package com.example.mainactivity

    import android.os.Bundle
    import android.view.animation.AnimationUtils
    import android.widget.*
    import androidx.activity.ComponentActivity

    class MainActivity : ComponentActivity() {

        private var currentImageIndex = 0
        private val images = arrayOf(R.drawable.neuvi, R.drawable.skirk2)
        private val imageNames = arrayOf("Neuvillette", "Skirk")

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            
            val autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.autoCompleteProvince)
            val provinces = resources.getStringArray(R.array.province)
            val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, provinces)
            autoCompleteTextView.setAdapter(adapter)
            autoCompleteTextView.threshold = 1
            
            val btnStandard: Button = findViewById(R.id.btnStandard)
            val btnImage: ImageButton = findViewById(R.id.btnImage)
            val btnToggle: ToggleButton = findViewById(R.id.btnToggle)
            val btnSwitch: Button = findViewById(R.id.btnSwitch)
            
            val imageSwitcher: ImageSwitcher = findViewById(R.id.imageSwitcher)
            
            imageSwitcher.setFactory {
                ImageView(this).apply {
                    scaleType = ImageView.ScaleType.CENTER_CROP
                    layoutParams = FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.MATCH_PARENT
                    )
                }
            }
            
            imageSwitcher.setImageResource(images[currentImageIndex])
            
            val fadeIn = AnimationUtils.loadAnimation(this, android.R.anim.fade_in)
            val fadeOut = AnimationUtils.loadAnimation(this, android.R.anim.fade_out)
            imageSwitcher.inAnimation = fadeIn
            imageSwitcher.outAnimation = fadeOut

            btnStandard.setOnClickListener {
                Toast.makeText(this, "Standard Button Clicked!", Toast.LENGTH_SHORT).show()
            }

            btnImage.setOnClickListener {
                Toast.makeText(this, "Image Button Clicked!", Toast.LENGTH_SHORT).show()
            }

            btnToggle.setOnCheckedChangeListener { _, isChecked ->
                val message = if (isChecked) "Toggle is ON" else "Toggle is OFF"
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }

            btnSwitch.setOnClickListener {
                currentImageIndex = (currentImageIndex + 1) % images.size
                imageSwitcher.setImageResource(images[currentImageIndex])
                Toast.makeText(
                    this,
                    "Switched to ${imageNames[currentImageIndex]}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
