# GuideDemo
## 一、展示效果
  
 ![image](https://github.com/onlybeyond/GuideDemo/blob/master/app/src/main/assets/guide.gif)
 
## 二、实现步骤分解<br>  
  1.实现滑动的ViewPager<br>  
  2.实现底部导航条<br>  
  3.实现下半部分图片的变化<br>  
  4.实现小猫动画<br>  
  
## 三、实现主要细节<br>  
   1.底部图片的处理<br>  
   1.1、 0.5和 2 * positionOffset,0.5是变化的节点，而透明度是0到1，所以是2 * positionOffset<br>  
   1.2、 透明度最小 0.2，当0.5或者接近。5时文字为看不到，所以这里设置最小值0.2<br>  
   1.3、 position和position+1的设定，这里建议大家多看看demo中的打印，效果会更直观<br>        
   2.底部导航条<br>  
   2.1、ViewPager从第一页到第二页View滑动的距离正常的话应该是自己的宽度所以distance设置为本身的宽度。<br>  
   2.2、positionOffset永远大于等于0，所以必须分正向和逆向,正向和逆向由上次滑动比例和本次滑动比例算出。<br>  
   2.3、去除等于0的情况，加上会有抖动，或者与想要的效果不符<br>  
   2.4、正向是position 逆向是position+1 .逆向时，如果当前是第二个页面，稍微往左一些potisition就会变为1.所以是position+1<br>  
   2.5、positionOffset - 1当运行demo时，可以注意一下打印注释，顺便关注一下上面说到的几个问题。这样会比较好理解代码为什么长这样<br>  
      
    
# License<br>

 
  
         Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
 
        http://www.apache.org/licenses/LICENSE-2.0
 
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
    Lock conversation

 
