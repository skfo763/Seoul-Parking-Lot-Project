package com.skfo763.core

class PermissionState(val type: Int) {

    enum class Type(val type: Int){
        GRANTED(0),
        DENIED_SHOW_AGAIN(1),
        DENIED_NEVER_SHOW(2);
    }

    companion object {
        fun getState(type: Int): Type {
            return when(type) {
                Type.GRANTED.type -> Type.GRANTED
                Type.DENIED_SHOW_AGAIN.type -> Type.DENIED_SHOW_AGAIN
                Type.DENIED_NEVER_SHOW.type -> Type.DENIED_NEVER_SHOW
                else -> Type.DENIED_SHOW_AGAIN
            }
        }
    }
}