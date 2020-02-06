package com.example.modulotechtest.ui.main.userfragment

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.lifecycle.ViewModel
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.modulotechtest.model.User
import com.example.modulotechtest.room.user.UserRepository
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val requestManager: RequestManager,
    private val userDrawable: Drawable
) : ViewModel() {


    private val user = userRepository.getUser()

    fun updateUser(user: User) {
        userRepository.update(user)
    }

    fun setFragmentDrawable(imageView: ImageView) {
        requestManager
            .load(userDrawable)
            .apply(RequestOptions.circleCropTransform())
            .into(imageView)
    }

    fun getUser() = user


}