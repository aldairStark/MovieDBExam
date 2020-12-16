package com.example.viewModel.retrofit.models

import android.accounts.AuthenticatorDescription
import java.util.*

data  class Images(var localUrl:String="",var remoteUri:String="", var description:String="", val dateTaken: Date=Date()) {

}