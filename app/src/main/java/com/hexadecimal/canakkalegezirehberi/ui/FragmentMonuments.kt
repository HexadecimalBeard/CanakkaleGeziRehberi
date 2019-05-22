package com.hexadecimal.canakkalegezirehberi.ui


import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.hexadecimal.canakkalegezirehberi.R
import com.hexadecimal.canakkalegezirehberi.RoomDB.MonumentsDB
import com.hexadecimal.canakkalegezirehberi.adapter.MonumentsAdapter
import com.hexadecimal.canakkalegezirehberi.dao.MonumentsDao
import com.hexadecimal.canakkalegezirehberi.model.MonumentsEntity
import kotlinx.android.synthetic.main.bottom_dialog_cart.*
import kotlinx.android.synthetic.main.fragment_monuments.*
import kotlinx.android.synthetic.main.monuments_detail_custom_dialog.*
import org.w3c.dom.Text
import java.io.Serializable
import kotlin.concurrent.thread


class FragmentMonuments : Fragment() {

    private var mediaPlayer: MediaPlayer? = null

    // TODO storage baglantisini burada yap
    // ses dosyasını storage dan almak icin kullanacagim
    //private val storage: FirebaseStorage by lazy { FirebaseStorage.getInstance() }

    private val monumentsDB: MonumentsDB? by lazy { MonumentsDB.getInstance(context!!) }

    // query leri calistiracagimiz dao ya eristik
    private val monumentsDao: MonumentsDao? by lazy { monumentsDB?.getMonumentsDao() }

    private val firestoreDb: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }

    //private val bottomDialog: BottomSheetDialogFragment by lazy { BottomDialogFragment.newInstance() }

    private val firebaseAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    private var selectedMonumentsList = ArrayList<Long>()

    private var monumentsSelected = HashMap<String, Any>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_monuments, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        createRoomDatabase()

        with(monument_Recycler_View) {
            adapter = MonumentsAdapter { monument ->

                getFirestoreData(monument)
            }
            layoutManager = LinearLayoutManager(activity!!)

        }

        // live data ile listeyi takip ediyoruz,
        // herhangi bir degisiklik oldugunda aninda guncellenecek
        monumentsDao?.getAllMonumentsList()
            ?.observe(this, Observer<List<MonumentsEntity>> { newMonumentsList ->

                (monument_Recycler_View.adapter as MonumentsAdapter).setNewMonumentsList(
                    newMonumentsList
                )
            })

        // to create bottom sheet dialog
        denemeCartButton.setOnClickListener {
            val menuFragment = BottomCartMenuFragment()
            menuFragment.show(activity!!.supportFragmentManager,"")
        }

    }

    // to get long description of monument from firestore
    @SuppressLint("ClickableViewAccessibility")
    private fun getFirestoreData(anitItem: MonumentsEntity) {

        val dialog = Dialog(activity!!)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.monuments_detail_custom_dialog)
        val detailAnitResmi = dialog.findViewById<ImageView>(R.id.anit_Resim_Monuments_DetImg)
        detailAnitResmi.setBackgroundResource(anitItem.anitResim)
        val detailAnitIsmi = dialog.findViewById<TextView>(R.id.anit_Ismı_Monuments_DetailTxt)
        detailAnitIsmi?.text = anitItem.anitIsmi
        val detailAnitAciklama =
            dialog.findViewById<TextView>(R.id.anit_Aciklama_Monuments_DetailTxt)

        // TODO hava durumu bilgisini almak icin gerekenleri yaz

        val detailRecordPlayButton = dialog.findViewById<ImageButton>(R.id.record_Play_ButtonImg)

        val detailAddMonument = dialog.findViewById<ImageButton>(R.id.monument_Detail_SepeteEkle)

        detailAddMonument.setOnClickListener {

            if(selectedMonumentsList.contains(anitItem._id)){
                Toast.makeText(activity,"Anit eklenmis",Toast.LENGTH_SHORT).show()
            }else {
                //selectedMonumentsList.add(anitItem._id)

                monumentsSelected["anitId"] = "${anitItem._id}"

                firestoreDb.collection("Kullanıcılar").document("${firebaseAuth.uid}").set(monumentsSelected)

                Log.e("Monuments List","${monumentsSelected.size}")

            }
        }


        mediaPlayer = MediaPlayer.create(context, R.raw.canakkaleses)

        detailRecordPlayButton.setOnClickListener {

            if (mediaPlayer!!.isPlaying){
                mediaPlayer!!.pause()
                mediaPlayer!!.seekTo(0)
            }else{
                mediaPlayer!!.start()
            }
        }


        // dialog will close when touch outside of it
        dialog.setCanceledOnTouchOutside(true)

        val tiklananAnit = anitItem.anitIsmi

        val docref = firestoreDb.collection("Anıtlar").document(tiklananAnit)

        docref.get()
            .addOnSuccessListener { document ->
                if (document != null) {

                    detailAnitAciklama.text = document.getString("Anıt Bilgisi")
                    //detailAnitAciklama.text = document.data.toString()
                } else {
                    // TODO document yoksa yapilacak
                }
            }
            .addOnFailureListener {
                Toast.makeText(activity, "Anıt Bilgisi Alınamadı!", Toast.LENGTH_SHORT).show()
            }

        dialog.show()

    }

    companion object {
        fun newInstance() = FragmentMonuments()
    }

    fun createRoomDatabase() {
        val canakkaleSehitligi = MonumentsEntity(
            1,
            "Çanakkale Şehitliği ve Şehitler Abidesi",
            "Çanakkale Şehitleri Anıtı, Çanakkale il sınırları içindeki Gelibolu" +
                    " Yarımadası'nda, Çanakkale Boğazı'nın ucunda Morto Koyu önündeki Hisarlık " +
                    "Tepe üzerinde yer alan anıt. ",
            R.drawable.anitdeneme,
            "Eceabat/Çanakkale",
            40.050251,
            26.219326
        )

        val troyaAntikKenti = MonumentsEntity(
            2,
            "Troya Antik Kenti",
            "Troya, dünyadaki en ünlü antik kentlerden birisidir. ",
            R.drawable.anitdeneme,
            "Merkez/Çanakkale",
            40.149329,
            26.40374
        )

        val namazgahTabyasi = MonumentsEntity(
            3,
            "Namazgah Tabyası",
            "Kilitbahir Kalesi’ni geçtikten sonra yolun deniz tarafında bulunan " +
                    "26 adet bonetten oluşan Namazgâh Tabyası’nın, Osmanlı Ordusunun ıslahı için " +
                    "gelen Baron De Tott’un da önerisiyle inşasına başlanmıştır.",
            R.drawable.anitdeneme,
            "Eceabat/Çanakkale",
            40.14611,
            26.38006
        )

        thread(start = true) {
            monumentsDao?.addNewMonument(canakkaleSehitligi)
            monumentsDao?.addNewMonument(troyaAntikKenti)
            monumentsDao?.addNewMonument(namazgahTabyasi)
        }
    }

    override fun onDetach() {
        super.onDetach()

        var monumentIdsList = HashMap<String, ArrayList<Long>>()
        Log.e("Monument Ids list", "$monumentIdsList")
    }

}