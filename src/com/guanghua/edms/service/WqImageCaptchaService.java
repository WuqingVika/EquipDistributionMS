package com.guanghua.edms.service;

import com.octo.captcha.service.image.ImageCaptchaService;

public abstract  interface WqImageCaptchaService extends ImageCaptchaService {
	public abstract  void removeCaptcha(String sessionId);
}
