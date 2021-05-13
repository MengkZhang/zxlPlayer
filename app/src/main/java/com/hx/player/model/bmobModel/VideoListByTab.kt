package com.hx.player.model.bmobModel

import android.os.Parcel
import android.os.Parcelable
import cn.bmob.v3.BmobObject

/**
 * Desc
 *
 * @author zhangxiaolin
 * Date 2021/5/13
 * Copyright © 川大智胜
 */
class VideoListByTab(
    var itemId: String?,
    var playUrl: String?,
    var descriptionPgc: String?,
    var detail: String?
) :
    BmobObject(), Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(itemId)
        parcel.writeString(playUrl)
        parcel.writeString(descriptionPgc)
        parcel.writeString(detail)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VideoListByTab> {
        override fun createFromParcel(parcel: Parcel): VideoListByTab {
            return VideoListByTab(parcel)
        }

        override fun newArray(size: Int): Array<VideoListByTab?> {
            return arrayOfNulls(size)
        }
    }
}