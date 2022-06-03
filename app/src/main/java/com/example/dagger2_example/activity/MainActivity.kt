package com.example.dagger2_example.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.dagger2_example.App
import com.example.dagger2_example.databinding.ActivityMainBinding
import com.example.dagger2_example.utils.UserResource
import com.example.dagger2_example.viewmodel.UserViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * DI texnalogiyasi - bu object tashqaridan o'ziga kerakli bo'lgan boshqa objectlarni olishi.
 */

/**
 * @Module and @Provides - bu dependency ta'minlaydigan klass va methodlarni yaratadi
 *
 * @Component - bu tanlangan modullarni ishlatish va dependency injectionni bajarish uchun yordam beradi
 *
 * @Inject - bu dependencyga murojat qiladi. Constructor, field yoki methodga qo'llanilishi mumkin. To'g'ridan to'g'ri
 *          methodlarni chaqirib beradi, objectlarimizni o'zi new qilib object olib beradi
 *
 * @Singlton - bu faqatgina bir marotaba object yaratib undan umumiy componenta ichida foydalanishga yordam beradi
 */

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var userViewModel: UserViewModel

    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        App.appComponent.inject(this)
        initViews()
    }

    private fun initViews() {

        lifecycleScope.launch {
            userViewModel.getStateFlow().collect {
                when (it) {
                    is UserResource.Loading -> {

                    }
                    is UserResource.Success -> {
                        Log.d(TAG, "initViews: ${it.list}")
                    }
                    is UserResource.Error -> {

                    }
                }
            }

        }

    }


}