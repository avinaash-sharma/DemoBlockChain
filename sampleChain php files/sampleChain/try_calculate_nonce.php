<?php
         $num = rand();
         $result = md5($num);
         $four = substr($result, 0, 4);
         $i=1000;
         
         while( $result != '0000') {
            $num=rand();
            $result=md5($num);
            $four=substr($result,0,4);
            echo ("i = $i");
            $i++;
         }
         
         echo ("Loop stopped as num = $num and firstFour no is = $four" );
         echo ("result = $result");
      ?>