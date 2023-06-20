/*
 * Copyright © 2022 SNOMED International
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import { Component, Inject, OnInit } from '@angular/core';
import {APP_CONFIG, AppConfig} from '../app.config';
import {MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-feedback-widget',
  templateUrl: './feedback-widget.component.html',
  styleUrls: ['./feedback-widget.component.css']
})
export class FeedbackWidgetComponent implements OnInit {
  feedbackUrl: string;

  constructor(@Inject(APP_CONFIG) private config: AppConfig,
              public dialogRef: MatDialogRef<FeedbackWidgetComponent>) {
    this.feedbackUrl = config.feedbackUrl;
  }

  ngOnInit(): void {
  }

  onClose() {
    this.dialogRef.close();
  }
}
