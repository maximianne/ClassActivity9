package com.example.kotlinexample2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONException
import org.json.JSONObject

class FragmentTwo: Fragment() {
    private val viewModel:UserViewModel by activityViewModels()
    private lateinit var  textView:TextView
    private val baseURL="http://api.zippopotam.us/us/"
    private val client= AsyncHttpClient()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_two, container,false)
        textView = view.findViewById(R.id.text_view_two)

        /**ONE WAY TO DO IT
            viewModel.getInformation().observe(viewLifecycleOwner, object:Observer<UserInformation>{
            override fun onChanged(t: UserInformation?) {
                if(t!=null){
                    textView.text=t.name + " : " + t.zipcode
                }
            }

        })**/

        //let -> scope function
        //the context object is available as an arugment called it
        //the return valuse is lamba result

        //?. --> safe call operator
//        viewModel.getInformation().observe(viewLifecycleOwner, Observer {
//            //it is a userInformation in this case
//            userInfo-> userInfo?.let {
//            textView.text=it.name + " : " + it.zipcode
//        }
//
//        })

        viewModel.getInformation().observe(viewLifecycleOwner, Observer { userInfo-> userInfo?.let{
            client.get(baseURL+it.zipcode, object: AsyncHttpResponseHandler(){
                override fun onSuccess(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    responseBody: ByteArray?) {
                        if (responseBody == null) {
                        textView.text = it.name + "error city does not exist"
                        }
                         else {
                            try {
                                val json = JSONObject(String(responseBody))
                                val jsonObject : JSONObject = json.getJSONArray("places").getJSONObject(0)
                                val city = jsonObject.getString("place name")
                                textView.text = it.name + ":" + city
                            }
                        catch (e: JSONException){
                            e.printStackTrace()
                        }
                    }
                }
                override fun onFailure(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    responseBody: ByteArray?,
                    error: Throwable?){
                    textView.text = it.name + "error city does not exist"
                }
            })
        }
        })
        return view
    }

}