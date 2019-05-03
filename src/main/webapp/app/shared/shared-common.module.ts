import { NgModule } from '@angular/core';

import { AccouchementV3SharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [AccouchementV3SharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [AccouchementV3SharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class AccouchementV3SharedCommonModule {}
