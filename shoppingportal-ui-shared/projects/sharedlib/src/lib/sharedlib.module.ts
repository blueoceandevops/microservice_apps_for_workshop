import {ModuleWithProviders, NgModule} from '@angular/core';
import {NotificationLoaderComponent} from "./components/notification.loader.component";
import {CommonModule} from "@angular/common";
import {CommonService} from "./services/common.service";
import {HttpService} from "./services/http.service";
import {AmexioWidgetModule} from "amexio-ng-extensions";
import {SharedService} from "./services/shared.service";
import {CookieService} from "ngx-cookie-service";

@NgModule({
  declarations: [NotificationLoaderComponent],
  imports: [CommonModule, AmexioWidgetModule],
  exports: [NotificationLoaderComponent],
  providers: [SharedService,CommonService, HttpService, CookieService]
})
export class SharedlibModule {

  static forRoot(): ModuleWithProviders {
    return {
      ngModule: SharedlibModule,
      providers: [SharedService]
    };
  }
}
