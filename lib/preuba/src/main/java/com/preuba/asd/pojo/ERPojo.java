
/*
Create on Sat Oct 31 15:39:18 ART 2020
*Copyright (C) 120.
@author ALEJANDRO
@author SUBERO_ANA
@author ANACODE AND IVANCODE
@since 11.0
@version1.0.0.0
@version  %I%, %G%
*<p>Description: PRUEBA DE ANOTACIONES </p>
*/

package com.preuba.asd.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import java.util.Date;



public class ERPojo implements Serializable {

private static final long serialVersionUID = -830528754191560930L;

		private Long id;

		public Long getId() { 
			return id;
		}
		public void setId(Long  id) {
			this.id = id;
		}
			public boolean equalsERPojo(Object o) {
				if (this == o) return true;
				if (o == null || getClass() != o.getClass()) return false;
					ERPojo erpojo = (ERPojo) o;
						return 			Objects.equals(id, erpojo.id);

}}
 /*
 Copyright (C) 2008 Google Inc.
* Licensed to the Apache Software Foundation (ASF) under one or more
* contributor license agreements.  See the NOTICE file distributed with
* this work for additional information regarding copyright ownership.
* The ASF licenses this file to You under the Apache License, Version 2.0
* (the "License"); you may not use this file except in compliance with
* the License.  You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

